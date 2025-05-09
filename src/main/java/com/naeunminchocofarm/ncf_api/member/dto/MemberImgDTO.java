package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberImgDTO {

  private final Integer imgId;
  private final Integer memberId;
  private final String originFileName;
  private final String attachedFileName;


  public MemberImgDTO(Integer imgId, Integer memberId, String originFileName, String attachedFileName) {
    this.imgId = imgId;
    this.memberId = memberId;
    this.originFileName = originFileName;
    this.attachedFileName = attachedFileName;
  }

  public Integer getImgId() {
    return imgId;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public String getOriginFileName() {
    return originFileName;
  }

  public String getAttachedFileName() {
    return attachedFileName;
  }


}
