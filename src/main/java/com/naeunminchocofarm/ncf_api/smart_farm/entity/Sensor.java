package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Sensor {
  private Integer id;
  private String name;
  private Integer sectionId;
  private Integer uuidId;
  private String sensorType;
  private String uuid; // uuid 문자열

  public Sensor() {}

  public Sensor(Integer id, String name, Integer sectionId, Integer uuidId, String sensorType, String uuid) {
    this.id = id;
    this.name = name;
    this.sectionId = sectionId;
    this.uuidId = uuidId;
    this.sensorType = sensorType;
    this.uuid = uuid;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getSectionId() {
    return sectionId;
  }

  public Integer getUuidId() {
    return uuidId;
  }

  public String getSensorType() {
    return sensorType;
  }

  public String getUuid() {
    return uuid;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSectionId(Integer sectionId) {
    this.sectionId = sectionId;
  }

  public void setUuidId(Integer uuidId) {
    this.uuidId = uuidId;
  }

  public void setSensorType(String sensorType) {
    this.sensorType = sensorType;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
