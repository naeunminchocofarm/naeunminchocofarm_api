package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;

import java.time.OffsetDateTime;

public class FarmDTO {
  private final Integer id;
  private final Integer uuidId;
  private final String farmName;
  private final String farmAddr;
  private final OffsetDateTime useDate;
  private final String crop;
  private final String status;
  private final Member member;

  public FarmDTO(Integer id, String farmName, Integer uuidId, String farmAddr, OffsetDateTime useDate, String crop, String status, Member member) {
    this.id = id;
    this.farmName = farmName;
    this.uuidId = uuidId;
    this.farmAddr = farmAddr;
    this.useDate = useDate;
    this.crop = crop;
    this.status = status;
    this.member = member;
  }

  public Integer getId() {
    return id;
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

  public String getStatus() {
    return status;
  }

  public Member getMember() {
    return member;
  }

  public static FarmDTO from(Farm farm, Member member) {
    return new FarmDTO(
            farm.getId(),
            farm.getFarmName(),
            farm.getUuidId(),
            farm.getFarmAddr(),
            farm.getUseDate(),
            farm.getCrop(),
            farm.getStatus(),
            member
    );
  }
}
