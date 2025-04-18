package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.humidity.entity.HumidData;
import com.naeunminchocofarm.ncf_api.humidity.mapper.HumidityMapper;
import com.naeunminchocofarm.ncf_api.ldr.entity.SunshineData;
import com.naeunminchocofarm.ncf_api.ldr.mapper.LdrMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SensorMapper;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoistureData;
import com.naeunminchocofarm.ncf_api.soil_moisture.mapper.SoilMoistureMapper;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTempData;
import com.naeunminchocofarm.ncf_api.temperature.mapper.TemperatureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
  private static final Logger log = LogManager.getLogger(SensorService.class);
  private final SensorMapper sensorMapper;
  private final TemperatureMapper tempMapper;
  private final HumidityMapper humidMapper;
  private final LdrMapper sunshineMapper;
  private final SoilMoistureMapper soilMoistureMapper;

  public SensorService(SensorMapper sensorMapper, TemperatureMapper tempMapper, HumidityMapper humidMapper, LdrMapper sunshineMapper, SoilMoistureMapper soilMoistureMapper) {
    this.sensorMapper = sensorMapper;
      this.tempMapper = tempMapper;
      this.humidMapper = humidMapper;
      this.sunshineMapper = sunshineMapper;
      this.soilMoistureMapper = soilMoistureMapper;

  }

  // 전체 센서 조회
  public List<SensorDTO> getAllSensors() {
    return sensorMapper.getAllSensors().stream()
            .map(SensorDTO::from)
            .collect(Collectors.toList());
  }

  //특정 section 센서 조회
  public List<SensorDTO> getSensorBySectionId(Integer sectionId) {
    return sensorMapper.getSensorBySectionId(sectionId).stream()
            .map(SensorDTO::from)
            .collect(Collectors.toList());
  }

  // 센서 등록
  public void insertSensor(SensorDTO sensorDTO) {
    // UUID 문자열 생성
    String generatedUuid = UUID.randomUUID().toString();

    Sensor sensor = new Sensor();
    sensor.setName(sensorDTO.getName());
    sensor.setSectionId(sensorDTO.getSectionId());
    sensor.setUuid(generatedUuid);
    sensor.setSensorType(sensorDTO.getSensorType());

    // Mapper 호출 → selectKey로 uuid_id 반환되고 farms 삽입
    sensorMapper.insertSensor(sensor);
  }

  //센서 수정
  public void updateSensor(SensorDTO dto) {
    Sensor sensor =new Sensor();
    sensor.setId(dto.getId());
    sensor.setName(dto.getName());
    sensor.setSensorType(dto.getSensorType());
    sensor.setUuidId(dto.getUuidId());
    sensorMapper.updateSensor(sensor);
  }

  //센서 삭제
  public void deleteSensor(Integer id) {
    sensorMapper.deleteSensor(id);
  }

  public void insertSensorDatas(List<SensorDataDTO> sensorDataDTOs) {
    var airTempDatas = new ArrayList<SensorDataDTO>();
    var humidityDatas = new ArrayList<SensorDataDTO>();
    var sunshineDatas = new ArrayList<SensorDataDTO>();
    var soilMoistureDatas = new ArrayList<SensorDataDTO>();
    sensorDataDTOs.forEach(data -> {
      switch (data.getName()){
        case "air_temp":
          airTempDatas.add(data);
          break;
        case "humidity":
          humidityDatas.add(data);
          break;
        case "ldr":
          sunshineDatas.add(data);
          break;
        case "soil_moisture":
          soilMoistureDatas.add(data);
          break;
        default:
          break;
      }
    });

    tempMapper.insertAll(airTempDatas.stream().map(x -> new AirTempData((Double)x.getValue(), x.getMeasuredAt(), x.getSensorUuid())).toList());
    humidMapper.insertAll(humidityDatas.stream().map(x -> new HumidData((Double)x.getValue(), x.getMeasuredAt(), x.getSensorUuid())).toList());
    sunshineMapper.insertAll(sunshineDatas.stream().map(x -> new SunshineData((Integer)x.getValue(), x.getMeasuredAt(), x.getSensorUuid())).toList());
    soilMoistureMapper.insertAll(soilMoistureDatas.stream().map(x -> new SoilMoistureData((Integer)x.getValue(), x.getMeasuredAt(), x.getSensorUuid())).toList());
  }
}