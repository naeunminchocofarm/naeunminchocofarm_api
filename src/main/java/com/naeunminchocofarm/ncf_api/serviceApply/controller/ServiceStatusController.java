package com.naeunminchocofarm.ncf_api.serviceApply.controller;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceStatus;
import com.naeunminchocofarm.ncf_api.serviceApply.service.ServiceStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceStatusController {
    private final ServiceStatusService serviceStatusService;

    public ServiceStatusController(ServiceStatusService serviceStatusService) {
        this.serviceStatusService = serviceStatusService;
    }

    @GetMapping("ggg")
    public ResponseEntity<List<ServiceStatus>> getServiceStatus() {
        return ResponseEntity.ok(serviceStatusService.getServiceStatus());
    }
}