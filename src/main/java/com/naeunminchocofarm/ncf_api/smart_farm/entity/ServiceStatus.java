package com.naeunminchocofarm.ncf_api.smart_farm.entity;

public class ServiceStatus {
  private Integer id;
  private Integer memberId;
  private String status;

  public ServiceStatus() {
  }

  public ServiceStatus(Integer id, Integer memberId, String status) {
    this.id = id;
    this.memberId = memberId;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}