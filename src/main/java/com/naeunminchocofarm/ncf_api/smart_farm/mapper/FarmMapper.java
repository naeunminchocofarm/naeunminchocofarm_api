package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface FarmMapper {

  // 스마트팜 전체 조회
  List<Farm> getAllFarms();

  //FarmWithMember 조회
  List<Farm> getFarmWithMember();

  // 스마트팜 상세 조회
  Farm getFarmDetailById(@Param("id") Integer id);

  //LoginId로 member_id 조회
  int getMemberIdByLoginId(@Param("loginId") String loginId);

  // 스마트팜 등록
  void insertFarm(Farm farm);

  // 스마트팜 수정
  void updateFarm(Farm farm);

  // 스마트팜 삭제
  void deleteFarm(@Param("id") Integer id);
}
