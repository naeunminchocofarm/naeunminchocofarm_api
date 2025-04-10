package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.FarmMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmService {

  private final FarmMapper farmMapper;

  public FarmService(FarmMapper farmMapper) {
    this.farmMapper = farmMapper;
  }

  // 전체 스마트팜 조회
  public List<Farm> getAllFarms() {
    return farmMapper.getAllFarms();
  }

  // 스마트팜 등록
  public void insertFarm(Farm farm) {
    farmMapper.insertFarm(farm);
  }
}