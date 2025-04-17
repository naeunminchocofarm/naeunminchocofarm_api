package com.naeunminchocofarm.ncf_api.serviceApply.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceStatusDTO;

import java.time.OffsetDateTime;

public class ServiceApply {
    private Integer id; // 기본 자동생성
    private Integer memberId; // 일반 유저는 서버에서 설정, 관리자일 경우 직접 지정 가능
    private String type; // 신청자 유형 (법인, 개인사업자, 개인)
    private String contactTell; // 실무자 연락처
    private String content; // 상담 내용
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private OffsetDateTime applicationAt; // 신청 일시 (관리자 직접 지정 가능)
    private String memo; // 관리자 메모 (선택)
    private ServiceStatus serviceStatus;
    private LoginInfo loginInfo; // 로그인된 회원 정보

    public ServiceApply() {
    }

    public ServiceApply(Integer id, Integer memberId, String type, String contactTell, String content, OffsetDateTime applicationAt, String memo, ServiceStatus serviceStatus, LoginInfo loginInfo) {
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

    public OffsetDateTime getApplicationAt() {
        return applicationAt;
    }

    public void setApplicationAt(OffsetDateTime applicationAt) {
        this.applicationAt = applicationAt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }
}
