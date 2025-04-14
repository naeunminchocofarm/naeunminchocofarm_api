package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectionController {

  private final SectionService sectionService;

  public SectionController(SectionService sectionService) {
    this.sectionService = sectionService;
  }

  // 모든 구역 조회
  @GetMapping("/sections")
  public List<Section> getAllSections() {
    return sectionService.getAllSections();
  }

  // 특정 farm 구역 상세 목록 조회
  @GetMapping("/sections/farm/{farmId}")
  public List<Section> getSectionsByFarmId(@PathVariable Integer farmId) {
    return sectionService.getSectionsByFarmId(farmId);
  }

  // 구역 등록
  @PostMapping("/sections")
  public void insertSection(@RequestBody Section section) {
    sectionService.insertSection(
            section.getName(),
            section.getFarmId()
            );
  }
}
