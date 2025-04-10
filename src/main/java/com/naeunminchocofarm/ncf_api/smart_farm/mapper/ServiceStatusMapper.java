package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.ServiceStatusDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.ServiceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceStatusMapper {

  // 서비스 신청 전체 조회
  List<ServiceStatus> getAllServices();

  // 서비스 신청
  void insertService(ServiceStatusDTO serviceStatusDTO);
}