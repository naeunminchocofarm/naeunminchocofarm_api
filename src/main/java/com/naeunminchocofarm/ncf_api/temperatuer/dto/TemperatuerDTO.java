package com.naeunminchocofarm.ncf_api.temperatuer.dto;

import java.time.OffsetDateTime;

public class TemperatuerDTO {
	//날짜시간
	//온도 (최저 최고 평균) > min max avg는 리액트
	private final OffsetDateTime measuredAt;
	private final double temperatuer;

	public OffsetDateTime getTempDay() {
		return measuredAt;
	}

	public double getTemperatuer() {
		return temperatuer;
	}

	public TemperatuerDTO(OffsetDateTime measuredAt, double temperatuer) {
		this.measuredAt = measuredAt;
		this.temperatuer = temperatuer;
	}
}
