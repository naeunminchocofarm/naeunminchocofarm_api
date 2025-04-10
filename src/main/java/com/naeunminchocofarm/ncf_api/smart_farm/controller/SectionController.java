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
  @GetMapping("")
  public List<Section> getAllSections() {
    return sectionService.getAllSections();
  }

  // 구역 등록
  @PostMapping("")
  public void createSection(@RequestBody Section section) {
    sectionService.insertSection(section);
  }
}
