package com.naeunminchocofarm.ncf_api.serviceApply.mapper;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceApplyMapper {
    public List<ServiceApply> selectServiceApplyList(); // 관리자 전체
    public ServiceApply selectServiceApplyDetail(@Param("id") Integer id); // 관리자 상세
    public void insertServiceApply(ServiceApply serviceApply); // insert
    public List<ServiceApply> showMyServiceApplyList(Integer memberId); // 사용자 마이페이지 목록
    public void updateServiceApply(ServiceApply serviceApply); // 관리자  수정
    public void deleteServiceApply(Integer id); //
}
