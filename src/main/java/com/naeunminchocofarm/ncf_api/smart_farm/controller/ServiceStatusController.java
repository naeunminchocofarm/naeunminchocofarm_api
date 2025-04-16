package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.ServiceStatus;
import com.naeunminchocofarm.ncf_api.smart_farm.service.ServiceStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceStatusController {

  private final ServiceStatusService serviceStatusService;

  public ServiceStatusController(ServiceStatusService serviceStatusService) {
    this.serviceStatusService = serviceStatusService;
  }

  // 서비스 신청 전체 조회
  @GetMapping("/applySmartFarm")
  public List<ServiceStatus> getAllServiceStatuses() {
    return serviceStatusService.getAllServiceStatuses();
  }

  // 서비스 신청 등록
  @PostMapping("/applySmartFarm")
  public void createService(@RequestBody ServiceStatus serviceStatus) {
    serviceStatusService.insertServiceStatus(serviceStatus);
  }
}
