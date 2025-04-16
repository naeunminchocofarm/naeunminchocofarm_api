package com.naeunminchocofarm.ncf_api.serviceApply.entity;

public class ServiceStatus {
    private Integer id;
    private String serviceStatus;

    public ServiceStatus(Integer id, String serviceStatus) {
        this.id = id;
        this.serviceStatus = serviceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
