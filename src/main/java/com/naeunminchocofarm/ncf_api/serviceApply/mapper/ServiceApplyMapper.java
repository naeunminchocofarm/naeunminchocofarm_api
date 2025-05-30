package com.naeunminchocofarm.ncf_api.serviceApply.mapper;

import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyDetailDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyListItemDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.SimpleServiceApplyDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ServiceApplyMapper {
    Optional<ServiceApplyDetailDTO> selectServiceApplyDetail(@Param("id") Integer id); // 관리자 상세
    List<ServiceApplyDetailDTO> findByMemberId(@Param("memberId") Integer memberId); // 사용자 마이페이지 목록
    void updateServiceApply(ServiceApply serviceApply); // 관리자  수정
    void deleteServiceApply(Integer id); //
    void insertServiceApplyV2(@Param("memberId") Integer memberId, @Param("data") SimpleServiceApplyDTO serviceApplyDto);
    List<ServiceApplyListItemDTO> selectServiceApplyList();
}
