package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.FarmMapper;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.UuidMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FarmService {

  private final FarmMapper farmMapper;
  private final UuidMapper uuidMapper;

  public FarmService(FarmMapper farmMapper, UuidMapper uuidMapper) {
    this.farmMapper = farmMapper;
    this.uuidMapper = uuidMapper;
  }

  // 스마트팜 전체 조회
  public List<FarmDTO> getAllFarms() {
    return farmMapper.getAllFarms().stream()
            .map(farm -> FarmDTO.from(farm, farm.getMember()))
            .collect(Collectors.toList());
  }

  //FarmWithMember 조회
  public List<FarmDTO> getFarmWithMember() {
    return farmMapper.getFarmWithMember().stream()
            .map(farm -> FarmDTO.from(farm, farm.getMember()))
            .collect(Collectors.toList());
  }

  // 스마트팜 상세 조회
  public FarmDTO getFarmDetailById(Integer id) {
    Farm farm = farmMapper.getFarmDetailById(id);
    return FarmDTO.from(farm, farm.getMember());
  }

  // 스마트팜 등록 (UUID 자동 등록 포함)
  public void insertFarm(FarmDTO farmDTO) {
    // UUID 문자열 생성
    String generatedUuid = UUID.randomUUID().toString();

    // UUID 테이블에 삽입
    uuidMapper.insertUuid(generatedUuid);

    // UUID ID 얻기
    Integer uuidId = uuidMapper.getLastInsertId();

    // farms 테이블에 스마트팜 삽입
    farmMapper.insertFarm(
            farmDTO.getMember().getId(),
            uuidId,
            farmDTO.getFarmName(),
            farmDTO.getFarmAddr(),
            farmDTO.getUseDate(),
            farmDTO.getCrop(),
            farmDTO.getStatus()
    );
  }

  // 스마트팜 수정
  public void updateFarm(FarmDTO farmDTO) {
    Farm farm = new Farm();
    farm.setId(farmDTO.getId());
    farm.setMemberId(farmDTO.getMember().getId());
    farm.setFarmName(farmDTO.getFarmName());
    farm.setUuidId(farmDTO.getUuidId());
    farm.setFarmAddr(farmDTO.getFarmAddr());
    farm.setUseDate(farmDTO.getUseDate());
    farm.setCrop(farmDTO.getCrop());
    farm.setStatus(farmDTO.getStatus());
    farmMapper.updateFarm(farm);
  }
  //스마트팜 삭제
  public void deleteFarm(Integer id) {
    farmMapper.deleteFarm(id);
  }
}
