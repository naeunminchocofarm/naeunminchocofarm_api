package com.naeunminchocofarm.ncf_api.soil_moisture.service;

import com.naeunminchocofarm.ncf_api.humidity.mapper.HumidityMapper;
import com.naeunminchocofarm.ncf_api.soil_moisture.mapper.SoilMoistureMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SoilMoistureService {
    private final SoilMoistureMapper soilMoistureMapper;

    public SoilMoistureService(SoilMoistureMapper soilMoistureMapper) {
        this.soilMoistureMapper = soilMoistureMapper;
    }

    public void insertSoilMoistureValue(Integer soilMoistureValue, OffsetDateTime measuredAt) {
        this.soilMoistureMapper.insert(soilMoistureValue, measuredAt);
    }
}
