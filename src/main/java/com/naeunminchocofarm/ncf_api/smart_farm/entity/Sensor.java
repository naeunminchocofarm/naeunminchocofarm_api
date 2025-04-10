package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Sensor {
  private Integer id;
  private String sensorName;
  private Integer sectionId;
  private Integer uuidId;
  private final String sensorType;

  public Sensor(String sensorType) {
      this.sensorType = sensorType;
  }

  public Sensor(Integer id, String sensorName, Integer sectionId, Integer uuidId, String sensorType) {
    this.id = id;
    this.sensorName = sensorName;
    this.sectionId = sectionId;
    this.uuidId = uuidId;
    this.sensorType = sensorType;
  }

  public Integer getId() {
    return id;
  }

  public String getSensorName() {
    return sensorName;
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
}
