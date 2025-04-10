package com.naeunminchocofarm.ncf_api.smart_farm.dto;

public class SectionDTO {
  private Integer id;
  private String sectionName;
  private Integer farmId;
  private Integer uuid;

  public SectionDTO() {
  }

  public SectionDTO(Integer  id, String sectionName, Integer farmId, Integer uuid) {
    this.id = id;
    this.sectionName = sectionName;
    this.farmId = farmId;
    this.uuid = uuid;
  }

  public Integer  getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  public Integer getFarmId() {
    return farmId;
  }

  public void setFarmId(Integer farmId) {
    this.farmId = farmId;
  }

  public Integer getUuid() {
    return uuid;
  }

  public void setUuid(Integer uuid) {
    this.uuid = uuid;
  }
}
