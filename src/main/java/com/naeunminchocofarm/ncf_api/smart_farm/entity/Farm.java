package com.naeunminchocofarm.ncf_api.smart_farm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naeunminchocofarm.ncf_api.member.entity.Member;

import java.time.OffsetDateTime;

public class Farm {
  private Integer id;
  private Integer memberId;
  private String farmName;
  private Integer uuidId;
  private String uuid;
  private String farmAddr;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
  private OffsetDateTime useDate;
  private String crop;
  private String status;
  private Member member;
  public Farm() {

  }

  public Farm(Integer id, Integer memberId, String farmName, Integer uuidId, String uuid, String farmAddr, OffsetDateTime useDate, String crop, Member member) {
    this.id = id;
    this.memberId = memberId;
    this.farmName = farmName;
    this.uuidId = uuidId;
    this.uuid = uuid;
    this.farmAddr = farmAddr;
    this.useDate = useDate;
    this.crop = crop;
    this.member = member;
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

  public String getFarmName() {
    return farmName;
  }

  public void setFarmName(String farmName) {
    this.farmName = farmName;
  }

  public Integer getUuidId() {
    return uuidId;
  }

  public void setUuidId(Integer uuidId) {
    this.uuidId = uuidId;
  }

  public String getFarmAddr() {
    return farmAddr;
  }

  public void setFarmAddr(String farmAddr) {
    this.farmAddr = farmAddr;
  }

  public OffsetDateTime getUseDate() {
    return useDate;
  }

  public void setUseDate(OffsetDateTime useDate) {
    this.useDate = useDate;
  }

  public String getCrop() {
    return crop;
  }

  public void setCrop(String crop) {
    this.crop = crop;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
