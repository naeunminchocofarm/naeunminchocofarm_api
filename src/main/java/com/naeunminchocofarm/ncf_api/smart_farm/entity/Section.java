package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Section {
  private Integer id;
  private String name;
  private Integer farmId;
  private Integer uuidId;
  private String uuid;


  public Section() {
  }

  public Section(Integer id, String name, Integer farmId, Integer uuidId, String uuid) {
    this.id = id;
    this.name = name;
    this.farmId = farmId;
    this.uuidId = uuidId;
    this.uuid = uuid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getFarmId() {
    return farmId;
  }

  public void setFarmId(Integer farmId) {
    this.farmId = farmId;
  }

  public Integer getUuidId() {
    return uuidId;
  }

  public void setUuidId(Integer uuidId) {
    this.uuidId = uuidId;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
