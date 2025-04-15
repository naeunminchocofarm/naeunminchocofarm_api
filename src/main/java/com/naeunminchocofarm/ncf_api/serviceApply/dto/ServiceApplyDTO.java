package com.naeunminchocofarm.ncf_api.serviceApply.dto;

import java.time.OffsetDateTime;

public class ServiceApplyDTO {
    private final Integer id; // ID
    private final Integer memberId; // 사용자 ID
    //private final MemberInfo memberInfo; // 사용자 정보 전체 (조회용-학원컴)
    private final ServiceStatusDTO serviceStatus;
    private final String type;
    private final String contactTell;
    private final String content;
    private final String memo;
    private final OffsetDateTime applicationDate;

                                                  //    MemberInfo memberInfo 추가되야함 일단빼둠
    public ServiceApplyDTO(Integer id, Integer memberId, ServiceStatusDTO serviceStatus, String type, String contactTell, String content, String memo, OffsetDateTime applicationDate) {
        this.id = id;
        this.memberId = memberId;
        //this.memberInfo = memberInfo;
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

    public Integer getMemberId() {
        return memberId;
    }

//    public MemberInfo getMember() {
//        return member;
//    }

    public ServiceStatusDTO getServiceStatus() {
        return serviceStatus;
    }

    public String getType() {
        return type;
    }

    public String getContactTell() {
        return contactTell;
    }

    public String getContent() {
        return content;
    }

    public String getMemo() {
        return memo;
    }

    public OffsetDateTime getApplicationDate() {
        return applicationDate;
    }
}
