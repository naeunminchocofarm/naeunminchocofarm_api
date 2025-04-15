package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.humidity.entity.HumidData;
import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import com.naeunminchocofarm.ncf_api.humidity.mapper.HumidityMapper;
import com.naeunminchocofarm.ncf_api.ldr.entity.LdrValue;
import com.naeunminchocofarm.ncf_api.ldr.entity.SunshineData;
import com.naeunminchocofarm.ncf_api.ldr.mapper.LdrMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SensorMapper;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoistureData;
import com.naeunminchocofarm.ncf_api.soil_moisture.mapper.SoilMoistureMapper;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTempData;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;
import com.naeunminchocofarm.ncf_api.temperature.mapper.TemperatureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
  public List<Sensor> getAllSensors() {
    return sensorMapper.getAllSensors();
  }

  // 센서 등록
  public void insertSensor(Sensor sensor) {
    sensorMapper.insertSensor(sensor);
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