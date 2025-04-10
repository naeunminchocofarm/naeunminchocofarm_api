package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Section {
  private Integer id;
  private String sectionName;
  private Integer farmId;
  private Integer uuidId;

  public Section() {
  }

  public Section(Integer id, String sectionName, Integer farmId, Integer uuidId) {
    this.id = id;
    this.sectionName = sectionName;
    this.farmId = farmId;
    this.uuidId = uuidId;
  }

  public Integer getId() {
    return id;
  }

  public String getSectionName() {
    return sectionName;
  }

  public Integer getFarmId() {
    return farmId;
  }

  public Integer getUuidId() {
    return uuidId;
  }
}
