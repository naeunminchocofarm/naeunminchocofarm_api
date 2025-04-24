package com.naeunminchocofarm.ncf_api.serviceApply.service;

import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyDetailDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.ServiceApplyListItemDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.dto.SimpleServiceApplyDTO;
import com.naeunminchocofarm.ncf_api.serviceApply.entity.ServiceApply;
import com.naeunminchocofarm.ncf_api.serviceApply.mapper.ServiceApplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceApplyService {
    private final ServiceApplyMapper serviceApplyMapper;

	public ServiceApplyService(ServiceApplyMapper serviceApplyMapper) {
		this.serviceApplyMapper = serviceApplyMapper;
	}

	public List<ServiceApplyListItemDTO> selectServiceApplyList(){
		return serviceApplyMapper.selectServiceApplyListV2();
	};

	public Optional<ServiceApplyDetailDTO> selectServiceApplyDetail(Integer id){
		return serviceApplyMapper.selectServiceApplyDetail(id);
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
    public void insertServiceApply(Integer memberId, SimpleServiceApplyDTO serviceApplyDto) {
		serviceApplyMapper.insertServiceApplyV2(memberId, serviceApplyDto);
    }
}
