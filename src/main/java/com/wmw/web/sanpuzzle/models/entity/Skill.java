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
package com.wmw.web.sanpuzzle.models.entity;

import static javax.persistence.FetchType.EAGER;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Skill extends AbstractPersistable<Long> {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "CHARACTER_ID")
  private Character character;

  private String name;

  private Integer turnMax;

  private Integer turnMin;

  @OneToMany(mappedBy = "skill", cascade = { CascadeType.ALL }, fetch = EAGER)
  private List<SkillStatement> statements;

  public Character getCharacter() {
    return character;
  }

  public void setCharacter(Character character) {
    this.character = character;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getTurnMax() {
    return turnMax;
  }

  public void setTurnMax(Integer turnMax) {
    this.turnMax = turnMax;
  }

  public Integer getTurnMin() {
    return turnMin;
  }

  public void setTurnMin(Integer turnMin) {
    this.turnMin = turnMin;
  }

  public List<SkillStatement> getStatements() {
    return statements;
  }

  public void setStatements(List<SkillStatement> statements) {
    this.statements = statements;
  }

}
