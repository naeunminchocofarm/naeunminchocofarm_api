package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SectionMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.UuidMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SectionService {

  private final SectionMapper sectionMapper;
  private final UuidMapper uuidMapper;

  public SectionService(SectionMapper sectionMapper, UuidMapper uuidMapper) {
    this.sectionMapper = sectionMapper;
    this.uuidMapper = uuidMapper;
  }

  // 전체 구역 조회
  public List<SectionDTO> getAllSections() {
    return sectionMapper.getAllSections().stream()
            .map(SectionDTO::from)
            .collect(Collectors.toList());
  }

  // 특정 farm의 구역 목록 조회
  public List<SectionDTO> getSectionsByFarmId(Integer farmId) {
    return sectionMapper.getSectionsByFarmId(farmId).stream()
            .map(SectionDTO::from)
            .collect(Collectors.toList());
  }

  // 구역 등록 (UUID 자동 등록 포함)
  public void insertSection(SectionDTO sectionDTO) {
    // UUID 문자열 생성
    String generatedUuid = UUID.randomUUID().toString();

    // UUID 테이블에 삽입
    uuidMapper.insertUuid(generatedUuid);

    // UUID ID 얻기
    Integer uuidId = uuidMapper.getLastInsertId();

    // sections 테이블에 구역 삽입
    sectionMapper.insertSection(
            sectionDTO.getName(),
            sectionDTO.getFarmId(),
            uuidId
    );
  }
}
