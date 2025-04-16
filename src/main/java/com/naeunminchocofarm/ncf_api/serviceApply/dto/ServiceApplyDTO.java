package com.naeunminchocofarm.ncf_api.serviceApply.dto;

import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.entity.MemberRole;

import java.time.OffsetDateTime;

public class ServiceApplyDTO {
    private final Integer id; // ID
    private final Integer memberId; // 사용자 ID
    private final String type;
    private final String contactTell;
    private final String content;
    private final String memo;
    private final OffsetDateTime applicationDate;
    private final ServiceStatusDTO serviceStatus;
    private final LoginInfoDTO loginInfoDTO;

    public ServiceApplyDTO(Integer id, Integer memberId, String type, String contactTell, String content, String memo, OffsetDateTime applicationDate, ServiceStatusDTO serviceStatus, LoginInfoDTO loginInfoDTO) {
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.contactTell = contactTell;
        this.content = content;
        this.memo = memo;
        this.applicationDate = applicationDate;
        this.serviceStatus = serviceStatus;
        this.loginInfoDTO = loginInfoDTO;
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

    public String getMemo() {
        return memo;
    }

    public OffsetDateTime getApplicationDate() {
        return applicationDate;
    }

    public ServiceStatusDTO getServiceStatus() {
        return serviceStatus;
    }

    public LoginInfoDTO getLoginInfoDTO() {
        return loginInfoDTO;
    }
}
