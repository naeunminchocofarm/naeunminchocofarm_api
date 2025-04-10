package com.naeunminchocofarm.ncf_api.smart_farm.entity;

import java.time.OffsetDateTime;

public class Farm {
  private Integer id;
  private Integer memberId;
  private String farmName;
  private Integer uuidId;
  private final String farmAddr;
  private final OffsetDateTime useDate;
  private final String crop;

  public Farm(String farmAddr, OffsetDateTime useDate, String crop) {
      this.farmAddr = farmAddr;
      this.useDate = useDate;
      this.crop = crop;
  }

  public Farm(Integer id, Integer memberId, String farmName, Integer uuidId, String farmAddr, OffsetDateTime useDate, String crop) {
    this.id = id;
    this.memberId = memberId;
    this.farmName = farmName;
    this.uuidId = uuidId;
    this.farmAddr = farmAddr;
    this.useDate = useDate;
    this.crop = crop;
  }

  public Integer getId() {
    return id;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public String getFarmName() {
    return farmName;
  }

  public Integer getUuidId() {
    return uuidId;
  }

  public String getFarmAddr() {
    return farmAddr;
  }

  public OffsetDateTime getUseDate() {
    return useDate;
  }

  public String getCrop() {
    return crop;
  }
}
