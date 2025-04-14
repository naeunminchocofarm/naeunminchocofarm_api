package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class Sensor {
  private Integer id;
  private String name;
  private Integer sectionId;
  private Integer uuidId;
  private String sensorType;

  public Sensor() {

  }

  public Sensor(String sensorType) {
      this.sensorType = sensorType;
  }

  public Sensor(Integer id, String name, Integer sectionId, Integer uuidId, String sensorType) {
    this.id = id;
    this.name = name;
    this.sectionId = sectionId;
    this.uuidId = uuidId;
    this.sensorType = sensorType;
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
}
