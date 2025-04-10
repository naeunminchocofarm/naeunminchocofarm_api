package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/farms")
public class FarmController {

  private final FarmService farmService;

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  // 모든 스마트팜 조회
  @GetMapping
  public List<Farm> getAllFarms() {
    return farmService.getAllFarms();
  }

  // 스마트팜 등록
  @PostMapping
  public void createFarm(@RequestBody FarmDTO farmDTO) {
    farmService.insertFarm(farmDTO);
  }
}
