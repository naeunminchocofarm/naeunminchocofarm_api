package com.naeunminchocofarm.ncf_api.serviceApply.dto;

public class ServiceStatusDTO {
    private final Integer id;
    private final String status;

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public ServiceStatusDTO(Integer id, String status) {
        this.id = id;
        this.status = status;
    }
}
