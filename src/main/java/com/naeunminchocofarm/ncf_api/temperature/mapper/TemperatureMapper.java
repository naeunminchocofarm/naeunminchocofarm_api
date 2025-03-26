package com.naeunminchocofarm.ncf_api.temperature.mapper;

import com.naeunminchocofarm.ncf_api.temperature.dto.TemperatureDTO;

import java.time.OffsetDateTime;
import java.util.List;

public interface TemperatureMapper {
	int getNowTemp();
	List<TemperatureDTO> getTodayTemp();
	//습도에서는 지금 전체 테이블을 가상으로 만든게하나있음

	//가상 온도 데이터를 넣을 예정===== ENTITY와 소통 =====
	void insert(Double value, OffsetDateTime tempMeasuredAt);
}
