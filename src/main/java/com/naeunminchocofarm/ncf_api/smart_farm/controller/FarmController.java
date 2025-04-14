package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  // 모든 스마트팜 조회
  @GetMapping
  public List<FarmDTO> getAllFarms() {
    return farmService.getAllFarms();
  }

  // FarmWithMember 조회
  @GetMapping("/with-member")
  public List<FarmDTO> getFarmWithMember() {
    return farmService.getFarmWithMember();
  }

  // 스마트팜 상세 조회
  @GetMapping("/{id}")
  public FarmDTO getFarmDetailById(@PathVariable Integer id) {
    return farmService.getFarmDetailById(id);
  }

  // 스마트팜 등록
  @PostMapping
  public void insertFarm(@RequestBody FarmDTO farmDTO) {
    farmService.insertFarm(farmDTO);
  }

  // 스마트팜 수정
  @PutMapping
  public void updateFarm(@RequestBody FarmDTO farmDTO) {
    farmService.updateFarm(farmDTO);
  }

  // 스마트팜 삭제
  @DeleteMapping("/{}")
  public void deleteFarm(@PathVariable Integer id) {
    farmService.deleteFarm(id);
  }
}
