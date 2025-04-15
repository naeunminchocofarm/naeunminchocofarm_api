package com.naeunminchocofarm.ncf_api.serviceApply.service;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceStatus;
import com.naeunminchocofarm.ncf_api.serviceApply.mapper.ServiceStatusMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStatusService {
    public ServiceStatusService(ServiceStatusMapper statusMapper) {
        this.statusMapper = statusMapper;
    }

    private final ServiceStatusMapper statusMapper;

    public List<ServiceStatus> getAllStatus() {
        return statusMapper.selectAllStatus();
    }
}
