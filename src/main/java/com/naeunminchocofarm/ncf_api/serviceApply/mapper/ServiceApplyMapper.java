package com.naeunminchocofarm.ncf_api.serviceApply.mapper;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface
ServiceApplyMapper {
    List<ServiceApply> selectServiceApplyList();
    ServiceApply selectServiceApplyDetail(Integer id);
    public void serviceApplication(ServiceApply serviceApply);
}
