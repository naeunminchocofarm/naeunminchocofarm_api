package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FarmController {

  private final FarmService farmService;

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  // 모든 스마트팜 조회
  @GetMapping("/farms")
  public List<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  // 스마트팜 등록
  @PostMapping("/farms")
  public void createFarm(@RequestBody Farm farm) {
    farmService.insertFarm(farm);
  }
}
