package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SensorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {

  private static final Logger log = LogManager.getLogger(SensorController.class);
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
        log.info("센서 데이터 수집!");
        sensorService.insertSensorDatas(sensorDataDTOs);
    }
}
