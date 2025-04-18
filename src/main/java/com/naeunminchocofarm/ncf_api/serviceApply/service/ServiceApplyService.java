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

	public List<ServiceApply> selectServiceApplyList(){
		return serviceApplyMapper.selectServiceApplyList();
	};
	public ServiceApply selectServiceApplyDetail(Integer id){
		return serviceApplyMapper.selectServiceApplyDetail(id);
	};
	public void insertServiceApply(ServiceApply serviceApply){
		serviceApplyMapper.insertServiceApply(serviceApply);
	};
	public List<ServiceApply> showMyServiceApplyList(Integer memberId){
		return serviceApplyMapper.showMyServiceApplyList(memberId);
	};
	public void updateServiceApply(ServiceApply serviceApply){
		serviceApplyMapper.updateServiceApply(serviceApply);
	};
	public void deleteServiceApply(Integer id){
		serviceApplyMapper.deleteServiceApply(id);
	};
}
