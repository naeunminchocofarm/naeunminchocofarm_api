package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SectionDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionController {

  private final SectionService sectionService;

  public SectionController(SectionService sectionService) {
    this.sectionService = sectionService;
  }

  // 모든 구역 조회
  @GetMapping
  public List<SectionDTO> getAllSections() {
    return sectionService.getAllSections();
  }

  // 특정 farm 구역 목록 조회
  @GetMapping("/farm/{farmId}")
  public List<SectionDTO> getSectionsByFarmId(@PathVariable Integer farmId) {
    return sectionService.getSectionsByFarmId(farmId);
  }

  // 구역 등록
  @PostMapping("/farm/{farmId}")
  public void insertSection(@PathVariable Integer farmId, @RequestBody SectionDTO sectionDTO) {
    SectionDTO dtoWithFarmId = new SectionDTO(
            sectionDTO.getId(),
            sectionDTO.getName(),
            farmId,
            sectionDTO.getUuidId()
    );
    sectionService.insertSection(dtoWithFarmId);
  }
}
