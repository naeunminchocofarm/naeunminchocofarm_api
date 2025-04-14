package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {

  private final SensorService sensorService;

  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

//  // 모든 센서 조회
//  @GetMapping("")
//  public List<Sensor> getAllSensors() {
//    return sensorService.getAllSensors();
//  }
//
//  // 센서 등록
//  @PostMapping("")
//  public void createSensor(@RequestBody SensorDTO sensorDTO) {
//    sensorService.insertSensor(sensorDTO);
//  }

    @PostMapping("/sensors/datas")
    public void insertSensorDatas(@RequestBody List<SensorDataDTO> sensorDataDTOs){
        sensorService.insertSensorDatas(sensorDataDTOs);
    }
}
