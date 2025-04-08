package com.naeunminchocofarm.ncf_api.ldr.service;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import com.naeunminchocofarm.ncf_api.ldr.mapper.LdrMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LdrService {
    private final LdrMapper ldrMapper;

    public LdrService(LdrMapper ldrMapper) {
        this.ldrMapper = ldrMapper;
    }

    @Deprecated()
    public void insertSunshineValue(String farmUuid, String cropName, String sectionName, String sensorName, Integer sunshineValue, OffsetDateTime measuredAt) {
        ldrMapper.insertSunshineValue(farmUuid, cropName, sectionName, sensorName, sunshineValue, measuredAt);
    }

    public List<LdrValueDTO> getAllLdrValue() {
        return ldrMapper.getAllLdrValue();
    }

    public void insertSunshineValue(Integer sunshineValue, OffsetDateTime measuredAt, String sensorUuid) {
        ldrMapper.insertSunshineValueV2(sunshineValue, measuredAt, sensorUuid);
    }
}
