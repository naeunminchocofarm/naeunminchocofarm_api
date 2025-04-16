package com.naeunminchocofarm.ncf_api.serviceApply.service;

import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import com.naeunminchocofarm.ncf_api.serviceApply.mapper.ServiceApplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceApplyService {
    private final ServiceApplyMapper serviceApplyMapper;

	public ServiceApplyService(ServiceApplyMapper serviceApplyMapper) {
		this.serviceApplyMapper = serviceApplyMapper;
	}


	public List<ServiceApply> getAllServiceApplies() {
        return serviceApplyMapper.selectServiceApplyList();
    }

    public ServiceApply selectServiceApplyDetail(Integer id){
        return serviceApplyMapper.selectServiceApplyDetail(id);
    }

    // 서비스 신청
    public void serviceApplication(ServiceApply serviceApply) {
        serviceApplyMapper.serviceApplication(serviceApply);
    }
}
