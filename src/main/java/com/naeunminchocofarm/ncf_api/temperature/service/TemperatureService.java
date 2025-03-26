package com.naeunminchocofarm.ncf_api.temperature.service;

import com.naeunminchocofarm.ncf_api.temperature.mapper.TemperatureMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TemperatureService {
    private final TemperatureMapper temperatureMapper;

    public TemperatureService(TemperatureMapper temperatureMapper) {
        this.temperatureMapper = temperatureMapper;
    }

    public void insertTemperature(Double temperature, OffsetDateTime measuredAt) {
        temperatureMapper.insert(temperature, measuredAt);
    }
}
