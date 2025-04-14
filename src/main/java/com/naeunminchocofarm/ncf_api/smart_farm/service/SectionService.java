package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SectionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SectionService {

  private final SectionMapper sectionMapper;

  public SectionService(SectionMapper sectionMapper) {
    this.sectionMapper = sectionMapper;
  }

  // 전체 구역 조회
  public List<Section> getAllSections() {
    return sectionMapper.getAllSections();
  }

  // 특정 farm 구역 상세 조회
  public List<Section> getSectionsByFarmId(Integer farmId) {
    return sectionMapper.getSectionsByFarmId(farmId);
  }

  // 구역 등록
  public void insertSection(String sectionName, Integer farmId) {
    Integer uuidId = UUID.randomUUID().hashCode();

    sectionMapper.insertSection(sectionName, farmId, uuidId);
  }
}