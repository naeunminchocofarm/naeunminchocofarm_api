package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Section {
  private Integer id;
  private String name;
  private Integer farmId;
  private Integer uuidId;

  public Section() {
  }

  public Section(Integer id, String name, Integer farmId, Integer uuidId) {
    this.id = id;
    this.name = name;
    this.farmId = farmId;
    this.uuidId = uuidId;
  }

  public Integer getId() {
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
}
