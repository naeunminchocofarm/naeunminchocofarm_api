package com.naeunminchocofarm.ncf_api.serviceApply.entity;

public class ServiceStatus {
    private Integer id;
    private String status;

    public ServiceStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
