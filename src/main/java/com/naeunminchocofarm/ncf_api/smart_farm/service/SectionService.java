package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.SectionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

  // 구역 등록
  public void insertSection(SectionDTO sectionDTO) {
    sectionMapper.insertSection(sectionDTO);
  }
}