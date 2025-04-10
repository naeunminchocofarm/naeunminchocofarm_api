package com.naeunminchocofarm.ncf_api.smart_farm.dto;

public class FarmDTO {
  private Integer id;
  private Integer  memberId;
  private String farmName;
  private Integer  uuid;

  public FarmDTO() {
  }

  public FarmDTO(Integer id, Integer  memberId, String farmName, Integer  uuid) {
    this.id = id;
    this.memberId = memberId;
    this.farmName = farmName;
    this.uuid = uuid;
  }

  public Integer  getId() {
    return id;
  }

  public void setId(Integer  id) {
    this.id = id;
  }

  public Integer  getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer  memberId) {
    this.memberId = memberId;
  }

  public String getFarmName() {
    return farmName;
  }

  public void setFarmName(String farmName) {
    this.farmName = farmName;
  }

  public Integer  getUuid() {
    return uuid;
  }

  public void setUuid(Integer  uuid) {
    this.uuid = uuid;
  }
}
