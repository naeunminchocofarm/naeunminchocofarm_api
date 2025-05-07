package com.naeunminchocofarm.ncf_api.member.entity;

public class MemberImg {
  private Integer imgId;
  private Integer memberId;
  private String originFileName;
  private String attachedFileName;


  public MemberImg() {}

  public MemberImg(Integer imgId, Integer memberId, String originFileName, String attachedFileName) {
    this.imgId = imgId;
    this.memberId = memberId;
    this.originFileName = originFileName;
    this.attachedFileName = attachedFileName;
  }

  public Integer getImgId() {
    return imgId;
  }

  public void setImgId(Integer imgId) {
    this.imgId = imgId;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public void setMemberId(Integer memberId) {
    this.memberId = memberId;
  }

  public String getOriginFileName() {
    return originFileName;
  }

  public void setOriginFileName(String originFileName) {
    this.originFileName = originFileName;
  }

  public String getAttachedFileName() {
    return attachedFileName;
  }

  public void setAttachedFileName(String attachedFileName) {
    this.attachedFileName = attachedFileName;
  }
}
