package com.naeunminchocofarm.ncf_api.smart_farm.service;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.ServiceStatus;
import com.naeunminchocofarm.ncf_api.smart_farm.mapper.ServiceStatusMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStatusService {
  private final ServiceStatusMapper serviceStatusMapper;

  public ServiceStatusService(ServiceStatusMapper serviceStatusMapper) {
    this.serviceStatusMapper = serviceStatusMapper;
  }


  //전체 서비스 신청 조회
  public List<ServiceStatus> getAllServiceStatuses() {
    return serviceStatusMapper.getAllServices();
  }

  // 서비스 신청
  public void insertServiceStatus(ServiceStatus serviceStatus) {
    serviceStatusMapper.insertService(serviceStatus);
  }
}