package com.naeunminchocofarm.ncf_api.temperature.entity;

import java.time.OffsetDateTime;

public class Temperature {
	private Integer id;
	private Double temperatuer;
	private OffsetDateTime tempMeasuredAt;

	public Integer getId() {
		return id;
	}

	public Double getTemperatuer() {
		return temperatuer;
	}

	public OffsetDateTime getTempMeasuredAt() {
		return tempMeasuredAt;
	}

	public Temperature(Integer id, Double temperatuer, OffsetDateTime tempMeasuredAt) {
		this.id = id;
		this.temperatuer = temperatuer;
		this.tempMeasuredAt = tempMeasuredAt;
	}
}
