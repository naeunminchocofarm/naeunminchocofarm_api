package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.FarmMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SectionMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SensorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class UuidService {

  private final FarmMapper farmMapper;
  private final SectionMapper sectionMapper;
  private final SensorMapper sensorMapper;

  public UuidService(FarmMapper farmMapper, SectionMapper sectionMapper, SensorMapper sensorMapper) {
    this.farmMapper = farmMapper;
    this.sectionMapper = sectionMapper;
    this.sensorMapper = sensorMapper;
  }

  @Transactional
  public void registerSmartFarm(FarmDTO farmDTO, SectionDTO sectionDTO, SensorDTO sensorDTO) {

    // 6자리 난수 기반 UUID 생성 (100000 ~ 999999)
    int uuid = new Random().nextInt(900000) + 100000;

    // 생성된 UUID를 모든 구성 요소에 설정
    farmDTO.setUuid(uuid);
    sectionDTO.setUuid(uuid);
    sensorDTO.setUuid(uuid);

    // Farm 등록
    farmMapper.insertFarm(farmDTO);

    // Section에 Farm ID 설정 후 등록
    sectionDTO.setFarmId(farmDTO.getId());
    sectionMapper.insertSection(sectionDTO);

    // Sensor에 Section ID 설정 후 등록
    sensorDTO.setSectionId(sectionDTO.getId());
    sensorMapper.insertSensor(sensorDTO);
  }
}