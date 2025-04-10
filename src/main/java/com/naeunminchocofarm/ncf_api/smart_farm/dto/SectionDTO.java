package com.naeunminchocofarm.ncf_api.smart_farm.dto;

public class SectionDTO {
  private final Integer id;
  private final String sectionName;
  private final Integer farmId;
  private final Integer uuidId;

  public SectionDTO(Integer  id, String sectionName, Integer farmId, Integer uuidId) {
    this.id = id;
    this.sectionName = sectionName;
    this.farmId = farmId;
    this.uuidId = uuidId;
  }

  public Integer  getId() {
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
