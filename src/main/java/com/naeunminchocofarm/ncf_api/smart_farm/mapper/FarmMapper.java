package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

  // 스마트팜 전체 조회
  List<Farm> getAllFarms();

  // 스마트팜 등록
  void insertFarm(Farm farm);
}
