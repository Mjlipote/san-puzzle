/**
 *
 * @author Wei-Ming Wu
 *
 *
 * Copyright 2015 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.wmw.web.sanpuzzle.controllers;

import static com.google.common.collect.Sets.newHashSet;
import static net.sf.rubycollect4j.RubyCollections.ra;
import static net.sf.rubycollect4j.RubyCollections.range;

import java.io.InputStream;
import java.util.Set;

import javax.annotation.PostConstruct;

import net.sf.rubycollect4j.RubyArray;
import net.sf.rubycollect4j.RubyHash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.wmw.web.sanpuzzle.Application;
import com.wmw.web.sanpuzzle.models.entity.Character;
import com.wmw.web.sanpuzzle.models.entity.Skill;
import com.wmw.web.sanpuzzle.models.entity.SkillStatement;
import com.wmw.web.sanpuzzle.models.entity.Strategy;
import com.wmw.web.sanpuzzle.models.entity.StrategyStatement;
import com.wmw.web.sanpuzzle.models.repo.CharacterRepository;

@Controller
public class SetupController {

  @Autowired
  CharacterRepository characterRepo;

  @PostConstruct
  void populateData() {
    range(1, 1100).each((i) -> {
      loadYaml(i);
    });
    postProcessData();
  }

  void postProcessData() {
    RubyArray<Character> chRa = ra(characterRepo.findAll());

    RubyHash<String, RubyArray<Character>> chRh = chRa.groupBy((ch) -> {
      return ch.getName();
    });

    // Set升級後名稱
    chRa.each((ch) -> {
      String preName;
      if ((preName = ch.getPreName()) != null) {
        if (chRh.keyʔ(preName)) {
          Character preCh = chRh.get(preName).first();
          preCh.setSuccName(ch.getName());
        }
      }
    });

    // Set最大稀有度
    chRa.each((ch) -> {
      String succName;
      if ((succName = ch.getSuccName()) == null) {
        ch.setFinalRarity(ch.getRarity());
      } else {
        Set<String> names = newHashSet();
        names.add(ch.getName());

        Character succCh;
        do {
          if (chRh.get(succName) == null)
            break;
          succCh = chRh.get(succName).first();
          if (!names.add(succName))
            break;
          succName = succCh.getSuccName();
        } while (succCh != null);

        ch.setFinalRarity(names.size() - 1 + ch.getRarity());
      }
    });

    // Set最終覺醒
    chRa.each((ch) -> {
      ch.setMaxRarity(ch.getRarity().equals(ch.getFinalRarity()));
    });

    characterRepo.save(chRa);
  }

  void loadYaml(int i) {
    InputStream ios = null;
    try {
      ios =
          Application.class.getClassLoader().getResourceAsStream(
              "character_data/" + i + ".yml");

      Constructor constructor = new Constructor(Character.class);
      TypeDescription characterDescription =
          new TypeDescription(Character.class);
      new TypeDescription(StrategyStatement.class);
      constructor.addTypeDescription(characterDescription);
      Yaml yaml = new Yaml(constructor);
      Character ch = (Character) yaml.load(ios);

      for (Skill skill : ch.getSkills()) {
        skill.setCharacter(ch);
        if (skill.getStatements() != null) {
          for (SkillStatement ss : skill.getStatements()) {
            if (ss != null)
              ss.setSkill(skill);
          }
        }
      }

      for (Strategy strategy : ch.getStrategies()) {
        strategy.setCharacter(ch);
        if (strategy.getStatements() != null) {
          for (StrategyStatement ss : strategy.getStatements()) {
            if (ss != null)
              ss.setStrategy(strategy);
          }
        }
      }

      characterRepo.save(ch);
    } catch (Exception e) {
      if (ios == null) {
        // System.err.println("No " + i + ".yml");
      } else {
        System.err.println("Fail to load " + i + ".yml");
      }
      // e.printStackTrace();
    }
  }

}
