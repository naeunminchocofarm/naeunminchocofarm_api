package com.naeunminchocofarm.ncf_api.soil_moisture.dto;

import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;

import java.time.OffsetDateTime;

public class SoilMoistureDTO {
	private final Integer id;
	private final Integer soilMoistureValue;
	private final OffsetDateTime measuredAt;

	public SoilMoistureDTO(Integer id, Integer soilMoistureValue, OffsetDateTime measuredAt) {
		this.id = id;
		this.soilMoistureValue = soilMoistureValue;
		this.measuredAt = measuredAt;
	}

	public Integer getId() {
		return id;
	}

	public Integer getSoilMoistureValue() {
		return soilMoistureValue;
	}

	public OffsetDateTime getMeasuredAt() {
		return measuredAt;
	}

	public static SoilMoistureDTO from(SoilMoisture soilmoisture) {
		return new SoilMoistureDTO(soilmoisture.getId(), soilmoisture.getSoilMoistureValue(), soilmoisture.getMeasuredAt());
	}

}
