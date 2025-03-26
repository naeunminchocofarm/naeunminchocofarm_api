package com.naeunminchocofarm.ncf_api.temperature.service;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
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

    public void insertTemperature(Double temperature, OffsetDateTime measuredAt) {
        temperatureMapper.insert(temperature, measuredAt);
    }

	public int getNowTemp() {
		int nowTemp = 0;
		return nowTemp;
	}

	public void getTodayTemp(){
		// List<TemperatureDTO>
		// 오늘날짜와 동일한 모든 수치값을 불러와서 시간단위로?
		// 여기서 넘기나 react에서 넘기나 고민해보기
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
}
