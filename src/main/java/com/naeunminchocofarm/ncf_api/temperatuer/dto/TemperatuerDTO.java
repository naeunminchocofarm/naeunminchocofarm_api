package com.naeunminchocofarm.ncf_api.temperatuer.dto;

import java.time.OffsetDateTime;

public class TemperatuerDTO {
	//날짜시간
	//온도 (최저 최고 평균) > min max avg는 리액트
	private final int Id;
	private final OffsetDateTime tempDay;
	private final double temperatuer;


	public TemperatuerDTO(int id, OffsetDateTime tempDay, double temperatuer) {
		Id = id;
		this.tempDay = tempDay;
		this.temperatuer = temperatuer;
	}
}
