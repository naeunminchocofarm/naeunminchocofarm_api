package com.naeunminchocofarm.ncf_api.humidity.service;

import com.naeunminchocofarm.ncf_api.humidity.dto.HumidityDTO;
import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import com.naeunminchocofarm.ncf_api.humidity.mapper.HumidityMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class HumidityService {
    private final HumidityMapper humidityMapper;

    public HumidityService(HumidityMapper humidityMapper) {
        this.humidityMapper = humidityMapper;
    }

    public List<HumidityDTO> getAllHumidity() {
        return humidityMapper.getAllHumidity().stream()
                .map(HumidityDTO::from)
                .toList();
    }
}
