package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Sensor;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SensorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  private final SensorService sensorService;

  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  // 모든 센서 조회
  @GetMapping
  public List<SensorDTO> getAllSensors() {
    return sensorService.getAllSensors();
  }

  // 특정 section 센서 조회
  @GetMapping("/section/{sectionId}")
  public  List<SensorDTO> getSensorBySectionId(@PathVariable Integer sectionId) {
    return sensorService.getSensorBySectionId(sectionId);
  }

  // 센서 등록
  @PostMapping("/section/{sectionId}")
  public void insertSensor(@PathVariable Integer sectionId ,@RequestBody SensorDTO sensorDTO) {
    SensorDTO dtoWhitSectionId = new SensorDTO(
            sensorDTO.getId(),
            sensorDTO.getName(),
            sectionId,
            sensorDTO.getUuidId(),
            sensorDTO.getSensorType()
    );
    sensorService.insertSensor(dtoWhitSectionId);
  }

  @PostMapping("/datas")
  public void insertSensorDatas(@RequestBody List<SensorDataDTO> sensorDataDTOs){
    sensorService.insertSensorDatas(sensorDataDTOs);
  }
}
