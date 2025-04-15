package com.naeunminchocofarm.ncf_api.serviceApply.mapper;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceStatusMapper {
    List<ServiceStatus> selectAllStatus();
}
