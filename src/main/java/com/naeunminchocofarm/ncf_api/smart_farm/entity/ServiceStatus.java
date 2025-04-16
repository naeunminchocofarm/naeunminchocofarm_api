package com.naeunminchocofarm.ncf_api.smart_farm.entity;

import java.time.OffsetDateTime;

public class ServiceStatus {
  private Integer id;
  private Integer memberId;
  private String status;
  private final String tell;
  private final String email;
  private final String content;
  private final OffsetDateTime applicationDate;

  public ServiceStatus(String tell, String email, String content, OffsetDateTime applicationDate) {
      this.tell = tell;
      this.email = email;
      this.content = content;
      this.applicationDate = applicationDate;
  }

  public ServiceStatus(Integer id, Integer memberId, String status, String tell, String email, String content, OffsetDateTime applicationDate) {
    this.id = id;
    this.memberId = memberId;
    this.status = status;
    this.tell = tell;
    this.email = email;
    this.content = content;
    this.applicationDate = applicationDate;
  }

  public Integer getId() {
    return id;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public String getStatus() {
    return status;
  }

  public String getTell() {
    return tell;
  }

  public String getEmail() {
    return email;
  }

  public String getContent() {
    return content;
  }

  public OffsetDateTime getApplicationDate() {
    return applicationDate;
  }
}