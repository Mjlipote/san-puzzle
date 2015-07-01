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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.wmw.web.sanpuzzle.models.Arm;
import com.wmw.web.sanpuzzle.models.Group;

@Entity
public class Character extends AbstractPersistable<Long> {

  private static final long serialVersionUID = 1L;

  @Column(unique = true)
  private Integer number;

  private String name;

  private String preName;

  private String succName;

  private Integer rarity;

  private Integer finalRarity;

  private Boolean maxRarity;

  @Enumerated(EnumType.STRING)
  private Group group;

  @Enumerated(EnumType.STRING)
  private Arm arm;

  private Integer maxLevel;
  private Integer capability;
  private Integer initHp;
  private Integer initAttack;
  private Integer initRecovery;
  private Integer finalHp;
  private Integer finalAttack;
  private Integer finalRecovery;
  private Integer breakHp;
  private Integer breakAttack;
  private Integer breakRecovery;

  private String description;

  @OneToMany(mappedBy = "character", cascade = { CascadeType.ALL },
      fetch = EAGER)
  private List<Skill> skills;

  @OneToMany(mappedBy = "character", cascade = { CascadeType.ALL },
      fetch = EAGER)
  private List<Strategy> strategies;

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public String getPreName() {
    return preName;
  }

  public void setPreName(String preName) {
    this.preName = preName;
  }

  public String getSuccName() {
    return succName;
  }

  public void setSuccName(String succName) {
    this.succName = succName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getRarity() {
    return rarity;
  }

  public void setRarity(Integer rarity) {
    this.rarity = rarity;
  }

  public Integer getFinalRarity() {
    return finalRarity;
  }

  public void setFinalRarity(Integer finalRarity) {
    this.finalRarity = finalRarity;
  }

  public Boolean getMaxRarity() {
    return maxRarity;
  }

  public void setMaxRarity(Boolean maxRarity) {
    this.maxRarity = maxRarity;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Arm getArm() {
    return arm;
  }

  public void setArm(Arm arm) {
    this.arm = arm;
  }

  public Integer getMaxLevel() {
    return maxLevel;
  }

  public void setMaxLevel(Integer maxLevel) {
    this.maxLevel = maxLevel;
  }

  public Integer getCapability() {
    return capability;
  }

  public void setCapability(Integer capability) {
    this.capability = capability;
  }

  public Integer getInitHp() {
    return initHp;
  }

  public void setInitHp(Integer initHp) {
    this.initHp = initHp;
  }

  public Integer getInitAttack() {
    return initAttack;
  }

  public void setInitAttack(Integer initAttack) {
    this.initAttack = initAttack;
  }

  public Integer getInitRecovery() {
    return initRecovery;
  }

  public void setInitRecovery(Integer initRecovery) {
    this.initRecovery = initRecovery;
  }

  public Integer getFinalHp() {
    return finalHp;
  }

  public void setFinalHp(Integer finalHp) {
    this.finalHp = finalHp;
  }

  public Integer getFinalAttack() {
    return finalAttack;
  }

  public void setFinalAttack(Integer finalAttack) {
    this.finalAttack = finalAttack;
  }

  public Integer getFinalRecovery() {
    return finalRecovery;
  }

  public void setFinalRecovery(Integer finalRecovery) {
    this.finalRecovery = finalRecovery;
  }

  public Integer getBreakHp() {
    return breakHp;
  }

  public void setBreakHp(Integer breakHp) {
    this.breakHp = breakHp;
  }

  public Integer getBreakAttack() {
    return breakAttack;
  }

  public void setBreakAttack(Integer breakAttack) {
    this.breakAttack = breakAttack;
  }

  public Integer getBreakRecovery() {
    return breakRecovery;
  }

  public void setBreakRecovery(Integer breakRecovery) {
    this.breakRecovery = breakRecovery;
  }

  public List<Skill> getSkills() {
    return skills;
  }

  public void setSkills(List<Skill> skills) {
    this.skills = skills;
  }

  public List<Strategy> getStrategies() {
    return strategies;
  }

  public void setStrategies(List<Strategy> strategies) {
    this.strategies = strategies;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}