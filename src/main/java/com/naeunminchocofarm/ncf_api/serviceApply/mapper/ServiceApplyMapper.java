package com.naeunminchocofarm.ncf_api.serviceApply.mapper;

import com.naeunminchocofarm.ncf_api.serviceApply.dto.SimpleServiceApplyDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServiceApplyMapper {
    List<ServiceApply> selectServiceApplyList(); // 관리자 전체
    ServiceApply selectServiceApplyDetail(@Param("id") Integer id); // 관리자 상세
//    void insertServiceApply(ServiceApply serviceApply); // insert
    List<ServiceApply> showMyServiceApplyList(Integer memberId); // 사용자 마이페이지 목록
    void updateServiceApply(ServiceApply serviceApply); // 관리자  수정
    void deleteServiceApply(Integer id); //
    void insertServiceApplyV2(@Param("memberId") Integer memberId, @Param("data") SimpleServiceApplyDTO serviceApplyDto);
}
