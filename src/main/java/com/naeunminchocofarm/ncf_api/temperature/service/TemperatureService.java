package com.naeunminchocofarm.ncf_api.temperature.service;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;
import com.naeunminchocofarm.ncf_api.temperature.mapper.TemperatureMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TemperatureService {
    private static final Logger log = LogManager.getLogger(TemperatureService.class);
    private final TemperatureMapper temperatureMapper;

    public TemperatureService(TemperatureMapper temperatureMapper) {
        this.temperatureMapper = temperatureMapper;
    }

    public List<AirTemperatureDTO> getRecentTemperaturesGroupedByHour(Pagination pagination) {
        log.info("hello, page = {}, size = {}", pagination.getPage(), pagination.getSize());
        return this.temperatureMapper.getRecentTemperaturesGroupedByHour(pagination).stream()
                .map(AirTemperatureDTO::from)
                .toList();
    }

    public List<AirTemperatureDTO> getTemperatures(Pagination pagination) {
        return this.temperatureMapper.getRecentTemperatures(pagination).stream()
                .map(AirTemperatureDTO::from)
                .toList();
    }

    public AirTemperature getRecentOneTemp() {
        return this.temperatureMapper.getRecentOneTemp();
    }
}
