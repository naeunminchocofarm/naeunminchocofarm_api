package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;

public class SensorDTO {
  private final Integer  id;
  private final String sensorName;
  private final Integer sectionId;
  private final Integer uuidId;
  private final String sensorType;

  public SensorDTO(Integer id, String sensorName, Integer sectionId, Integer uuidId, String sensorType) {
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

  public static SensorDTO from(Sensor sensor) {
    return new SensorDTO(
            sensor.getId(),
            sensor.getSensorName(),
            sensor.getSectionId(),
            sensor.getUuidId(),
            sensor.getSensorType()
    );
  }

}