package com.naeunminchocofarm.ncf_api.iot.dto;

import com.naeunminchocofarm.ncf_api.iot.entity.AirTemperature;

import java.time.OffsetDateTime;

public class AirTemperatureDTO {
    private final Integer id;
    private final Float temperature;
    private final OffsetDateTime measuredAt;

    public AirTemperatureDTO(Integer id, Float temperature, OffsetDateTime measuredAt) {
        this.id = id;
        this.temperature = temperature;
        this.measuredAt = measuredAt;
    }

    public static AirTemperatureDTO from(AirTemperature airTemperature) {
        return new AirTemperatureDTO(airTemperature.getId(), airTemperature.getTemperature(), airTemperature.getMeasuredAt());
    }

    public Integer getId() {
        return id;
    }

    public Float getTemperature() {
        return temperature;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }
}
