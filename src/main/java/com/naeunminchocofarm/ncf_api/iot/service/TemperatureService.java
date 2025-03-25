package com.naeunminchocofarm.ncf_api.iot.service;

import com.naeunminchocofarm.ncf_api.iot.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.iot.mapper.TemperatureMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TemperatureService {
    private TemperatureMapper temperatureMapper;

    public TemperatureService(@Qualifier("inmemoryTemperatureMapper") TemperatureMapper temperatureMapper) {
        this.temperatureMapper = temperatureMapper;
    }

    public void insertTemperature(Float temperature, OffsetDateTime measuredAt) {
        temperatureMapper.insert(temperature, measuredAt);
    }

    public List<AirTemperatureDTO> getAll() {
        return temperatureMapper.getAll().stream()
                .map(AirTemperatureDTO::from)
                .toList();
    }
}
