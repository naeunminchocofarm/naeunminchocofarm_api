package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SensorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  private static final Logger log = LogManager.getLogger(SensorController.class);
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
            sensorDTO.getSensorType(),
            sensorDTO.getUuid()
    );
    sensorService.insertSensor(dtoWhitSectionId);
  }

  //센서 수정
  @PutMapping("/{id}")
  public void updateSensor(@PathVariable Integer id, @RequestBody SensorDTO sensorDTO) {
    SensorDTO dto = new SensorDTO(
            id,
            sensorDTO.getName(),
            sensorDTO.getSectionId(),
            sensorDTO.getUuidId(),
            sensorDTO.getSensorType(),
            sensorDTO.getUuid()
    );
    sensorService.updateSensor(dto);
  }

  //센서 삭제
  @DeleteMapping("/{id}")
  public void deleteSensor(@PathVariable Integer id) {
    sensorService.deleteSensor(id);
  }

  @PostMapping("/datas")
  public void insertSensorDatas(@RequestBody List<SensorDataDTO> sensorDataDTOs){
    sensorService.insertSensorDatas(sensorDataDTOs);
  }
}
