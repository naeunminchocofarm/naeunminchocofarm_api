package com.naeunminchocofarm.ncf_api.serviceApply.entity;

import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceStatusDTO;

import java.time.OffsetDateTime;

public class ServiceApply {
    private Integer id; // ID
    private Integer memberId; // 사용자 ID
    private String type;
    private String contactTell;
    private String content;
    private String memo;
    private OffsetDateTime applicationDate;
    private ServiceStatusDTO serviceStatus;
    private LoginInfoDTO loginInfoDTO;

    public ServiceApply(Integer id, Integer memberId, String type, String contactTell, String content, String memo, OffsetDateTime applicationDate, ServiceStatusDTO serviceStatus, LoginInfoDTO loginInfoDTO) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public ServiceStatusDTO getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatusDTO serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public LoginInfoDTO getLoginInfoDTO() {
        return loginInfoDTO;
    }

    public void setLoginInfoDTO(LoginInfoDTO loginInfoDTO) {
        this.loginInfoDTO = loginInfoDTO;
    }
}
