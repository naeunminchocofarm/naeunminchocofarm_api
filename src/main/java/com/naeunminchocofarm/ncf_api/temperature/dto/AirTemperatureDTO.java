package com.naeunminchocofarm.ncf_api.temperature.dto;

import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;

import java.time.OffsetDateTime;

public class AirTemperatureDTO {
    private final Integer id;
    private final Double temperatureC;
    private final OffsetDateTime measuredAt;

    public AirTemperatureDTO(Integer id, Double temperatureC, OffsetDateTime measuredAt) {
        this.id = id;
        this.temperatureC = temperatureC;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Double getTemperatureC() {
        return temperatureC;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public static AirTemperatureDTO from(AirTemperature airTemperature) {
        return new AirTemperatureDTO(airTemperature.getId(), airTemperature.getTemperatureC(), airTemperature.getMeasuredAt());
    }
}
