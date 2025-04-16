package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;

import java.time.OffsetDateTime;

public class FarmDTO {
  private final Integer id;
  private final Integer uuidId;
  private final String uuid;
  private final String farmName;
  private final String farmAddr;
  private final OffsetDateTime useDate;
  private final String crop;
  private final String status;
  private final MemberDTO member;

  public FarmDTO(Integer id, String farmName, Integer uuidId,String uuid, String farmAddr, OffsetDateTime useDate, String crop, String status, MemberDTO member) {
    this.id = id;
    this.farmName = farmName;
    this.uuidId = uuidId;
    this.uuid = uuid;
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

  public MemberDTO getMember() {
    return member;
  }

  public String getUuid() {
    return uuid;
  }

  public static FarmDTO from(Farm farm, MemberDTO member) {
    return new FarmDTO(
            farm.getId(),
            farm.getFarmName(),
            farm.getUuidId(),
            farm.getUuid(),
            farm.getFarmAddr(),
            farm.getUseDate(),
            farm.getCrop(),
            farm.getStatus(),
            member
    );
  }
}
