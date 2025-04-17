package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;

public class SectionDTO {
  private final Integer id;
  private final String name;
  private final Integer farmId;
  private final Integer uuidId;
  private final String uuid;


  public SectionDTO(Integer  id, String name, Integer farmId, Integer uuidId, String uuid
) {
    this.id = id;
    this.name = name;
    this.farmId = farmId;
    this.uuidId = uuidId;
    this.uuid = uuid;

  }

  public Integer  getId() {
    return id;
  }


  public String getName() {
    return name;
  }


  public Integer getFarmId() {
    return farmId;
  }


  public Integer getUuidId() {
    return uuidId;
  }

  public String getUuid() {
    return uuid;
  }

  public static SectionDTO from(Section section) {
    return new SectionDTO(
            section.getId(),
            section.getName(),
            section.getFarmId(),
            section.getUuidId(),
            section.getUuid()
    );
  }

}
