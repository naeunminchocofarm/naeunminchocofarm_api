package com.naeunminchocofarm.ncf_api.temperature.service;

import com.naeunminchocofarm.ncf_api.temperature.dto.TemperatureDTO;
import com.naeunminchocofarm.ncf_api.temperature.mapper.TemperatureMapper;

import java.util.List;

public class TemperatureService {
	private final TemperatureMapper temperatureMapper;

	public TemperatureService(TemperatureMapper temperatureMapper) {
		this.temperatureMapper = temperatureMapper;
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
}
