package com.naeunminchocofarm.ncf_api.smart_farm.controller;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.ServiceStatusDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.ServiceStatus;
import com.naeunminchocofarm.ncf_api.smart_farm.service.ServiceStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/services")
public class ServiceStatusController {

  private final ServiceStatusService serviceStatusService;

  public ServiceStatusController(ServiceStatusService serviceStatusService) {
    this.serviceStatusService = serviceStatusService;
  }

  // 서비스 신청 전체 조회
  @GetMapping
  public List<ServiceStatus> getAllServiceStatuses() {
    return serviceStatusService.getAllServiceStatuses();
  }

  // 서비스 신청 등록
  @PostMapping
  public void createService(@RequestBody ServiceStatusDTO serviceStatusDTO) {
    serviceStatusService.insertServiceStatus(serviceStatusDTO);
  }
}
