package com.naeunminchocofarm.ncf_api.serviceApply.dto;

public class ServiceStatusDTO {
    private final Integer id;
    private final String serviceStatus;

    public ServiceStatusDTO(Integer id, String serviceStatus) {
        this.id = id;
        this.serviceStatus = serviceStatus;
    }

    public Integer getId() {
        return id;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }
}
