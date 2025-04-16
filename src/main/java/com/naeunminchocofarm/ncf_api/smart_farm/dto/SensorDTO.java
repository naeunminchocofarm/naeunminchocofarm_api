package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;

public class SensorDTO {
  private final Integer id;
  private final String name;
  private final Integer sectionId;
  private final Integer uuidId;
  private final String sensorType;
  private final String uuid; // uuid 문자열

  public SensorDTO(Integer id, String name, Integer sectionId, Integer uuidId, String sensorType, String uuid) {
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

  public static SensorDTO from(Sensor sensor) {
    return new SensorDTO(
            sensor.getId(),
            sensor.getName(),
            sensor.getSectionId(),
            sensor.getUuidId(),
            sensor.getSensorType(),
            sensor.getUuid()
    );
  }
}
