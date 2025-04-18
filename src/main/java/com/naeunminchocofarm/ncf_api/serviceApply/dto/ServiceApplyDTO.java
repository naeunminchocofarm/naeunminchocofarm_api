package com.naeunminchocofarm.ncf_api.serviceApply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceStatus;

import java.time.OffsetDateTime;

public class ServiceApplyDTO {
    private final Integer id;
    private final Integer memberId; // 일반 유저는 서버에서, 관리자일 경우 직접 지정 가능
    private final String type; // 신청자 유형 (법인, 개인사업자, 개인)
    private final String contactTell; // 실무자 연락처
    private final String content; // 상담 내용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final OffsetDateTime applicationAt;
    private final String memo;
    private final ServiceStatus serviceStatus;
    private final LoginInfo loginInfo;

    public ServiceApplyDTO(Integer id, Integer memberId, String type, String contactTell, String content, OffsetDateTime applicationAt, String memo, ServiceStatus serviceStatus, LoginInfo loginInfo) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.contactTell = contactTell;
        this.content = content;
        this.applicationAt = applicationAt;
        this.memo = memo;
        this.serviceStatus = serviceStatus;
        this.loginInfo = loginInfo;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMemberId() {
        return memberId;
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

    public OffsetDateTime getApplicationAt() {
        return applicationAt;
    }

    public String getMemo() {
        return memo;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }
}
