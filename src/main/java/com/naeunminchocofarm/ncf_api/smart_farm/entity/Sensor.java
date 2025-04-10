package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Sensor {
  private Integer id;
  private String sensorName;
  private Integer sectionId;
  private Integer uuid;

  public Sensor() {
  }

  public Sensor(Integer id, String sensorName, Integer sectionId, Integer uuid) {
    this.id = id;
    this.sensorName = sensorName;
    this.sectionId = sectionId;
    this.uuid = uuid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSensorName() {
    return sensorName;
  }

  public void setSensorName(String sensorName) {
    this.sensorName = sensorName;
  }

  public Integer getSectionId() {
    return sectionId;
  }

  public void setSectionId(Integer sectionId) {
    this.sectionId = sectionId;
  }

  public Integer getUuid() {
    return uuid;
  }

  public void setUuid(Integer uuid) {
    this.uuid = uuid;
  }
}
