package com.naeunminchocofarm.ncf_api.soil_moisture.service;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.soil_moisture.dto.SoilMoistureDTO;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import com.naeunminchocofarm.ncf_api.soil_moisture.mapper.SoilMoistureMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SoilMoistureService {
    private final SoilMoistureMapper soilMoistureMapper;

    public SoilMoistureService(SoilMoistureMapper soilMoistureMapper) {
        this.soilMoistureMapper = soilMoistureMapper;
    }

    public void insertSoilMoistureValue(Integer soilMoistureValue, OffsetDateTime measuredAt) {
        this.soilMoistureMapper.insert(soilMoistureValue, measuredAt);
    }

    //라스트 원 오늘꺼
    public List<SoilMoistureDTO> getTodayValue(Pagination pagination) {
        return this.soilMoistureMapper.getTodaySoilValue(pagination).stream() //반환된 리스트를 스트림으로 변환
                .map(SoilMoistureDTO::from) // dto에서 entity를 받아 변환해서가능
                .toList();
    }

    //시간당으로 묶은거
    public List<SoilMoistureDTO> getRecentSoilGroupedByHour(Pagination pagination){
        return this.soilMoistureMapper.getRecentSoilGroupedByHour(pagination).stream()
                .map(SoilMoistureDTO::from)
                .toList();
    }

}
