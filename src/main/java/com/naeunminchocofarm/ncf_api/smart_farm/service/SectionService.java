package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SectionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SectionService {

  private final SectionMapper sectionMapper;

  public SectionService(SectionMapper sectionMapper) {
    this.sectionMapper = sectionMapper;
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

    // sections 테이블에 구역 삽입
    Section section = new Section();
    section.setName(sectionDTO.getName());
    section.setFarmId(sectionDTO.getFarmId());
    section.setUuid(generatedUuid);

    sectionMapper.insertSection(section);

  }

  //구역 수정
  public void updateSection(Integer id, String name) {
    sectionMapper.updateSection(id, name);
  }

  //구역 삭제
  public void deleteSection(Integer id) {
    sectionMapper.deleteSection(id);
  }
}
