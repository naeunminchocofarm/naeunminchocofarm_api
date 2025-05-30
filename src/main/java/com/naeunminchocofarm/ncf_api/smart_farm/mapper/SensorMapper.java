package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SensorMapper {

  // 센서 전체 조회
  List<Sensor> getAllSensors();

  //특정 section 센서 조회
  List<Sensor> getSensorBySectionId(@Param("sectionId") Integer sectionId);

  // 센서 등록
  void insertSensor(Sensor sensor);

  // 센서 수정
  void updateSensor(Sensor sensor);

  // 센서 삭제
  void deleteSensor(@Param("id") Integer id);
}
