package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SensorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

  private final SensorMapper sensorMapper;

  public SensorService(SensorMapper sensorMapper) {
    this.sensorMapper = sensorMapper;
  }

  // 전체 센서 조회
  public List<Sensor> getAllSensors() {
    return sensorMapper.getAllSensors();
  }

  // 센서 등록
  public void insertSensor(SensorDTO sensorDTO) {
    sensorMapper.insertSensor(sensorDTO);
  }
}