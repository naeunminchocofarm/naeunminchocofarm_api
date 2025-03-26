package com.naeunminchocofarm.ncf_api.temperature.mapper;

import com.naeunminchocofarm.ncf_api.temperature.dto.TemperatureDTO;

import java.time.OffsetDateTime;
import java.util.List;

public class InMemoryTempMapper implements TemperatureMapper {
	//여기서 implement 해줘야함
	@Override
	public int getNowTemp() {
		int nowTemp = 0;
		return nowTemp;
	}

	@Override
	public List<TemperatureDTO> getTodayTemp() {
		return List.of();
	}

	//가상데이터를 만들어 넣어볼 영역 > 이따가
	@Override
	public void insert(Double value, OffsetDateTime tempMeasuredAt) {

	}

}
