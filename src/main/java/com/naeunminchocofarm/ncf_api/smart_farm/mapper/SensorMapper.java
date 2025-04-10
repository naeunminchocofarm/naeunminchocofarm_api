package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SensorMapper {

  // 센서 전체 조회
  List<Sensor> getAllSensors();

  // 센서 등록
  void insertSensor(Sensor sensor);
}
