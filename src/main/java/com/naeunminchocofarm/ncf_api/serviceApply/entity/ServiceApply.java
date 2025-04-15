package com.naeunminchocofarm.ncf_api.serviceApply.entity;

import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceStatusDTO;

import java.time.OffsetDateTime;

public class ServiceApply {
    private Integer id; // ID
    private Integer memberId; // 사용자 ID
    //private MemberInfo memberInfo; // 사용자 정보 전체 (조회용-학원컴)
    private ServiceStatusDTO serviceStatus;
    private String type;
    private String contactTell;
    private String content;
    private String memo;
    private OffsetDateTime applicationDate;

    public ServiceApply(Integer id, Integer memberId, ServiceStatusDTO serviceStatus, String type, String contactTell, String content, String memo, OffsetDateTime applicationDate) {
        this.id = id;
        this.memberId = memberId;
        this.serviceStatus = serviceStatus;
        this.type = type;
        this.contactTell = contactTell;
        this.content = content;
        this.memo = memo;
        this.applicationDate = applicationDate;
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

//    public MemberInfo getMemberInfo() {
//        return memberInfo;
//    }
//
//    public void setMemberInfo(MemberInfo memberInfo) {
//        this.memberInfo = memberInfo;
//    }

    public ServiceStatusDTO getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatusDTO serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactTell() {
        return contactTell;
    }

    public void setContactTell(String contactTell) {
        this.contactTell = contactTell;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public OffsetDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(OffsetDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
