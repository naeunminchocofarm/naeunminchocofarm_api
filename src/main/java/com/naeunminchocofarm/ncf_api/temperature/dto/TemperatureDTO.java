package com.naeunminchocofarm.ncf_api.temperature.dto;

import java.time.OffsetDateTime;

public class TemperatureDTO {
	//날짜시간
	//온도 (최저 최고 평균) > min max avg는 리액트
	private final OffsetDateTime tempMeasuredAt;
	private final double temperatuer;

	public OffsetDateTime getTempDay() {
		return tempMeasuredAt;
	}

	public double getTemperatuer() {
		return temperatuer;
	}

	public TemperatureDTO(OffsetDateTime tempMeasuredAt, double temperatuer) {
		this.tempMeasuredAt = tempMeasuredAt;
		this.temperatuer = temperatuer;
	}
}
