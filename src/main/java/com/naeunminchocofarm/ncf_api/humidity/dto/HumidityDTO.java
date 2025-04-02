package com.naeunminchocofarm.ncf_api.humidity.dto;

import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;

import java.time.OffsetDateTime;

public class HumidityDTO {
    private final Double humidityPercentage;
    private final OffsetDateTime measuredAt;

    public HumidityDTO(Double value, OffsetDateTime measuredAt) {
        this.humidityPercentage = value;
        this.measuredAt = measuredAt;
    }

    public Double getHumidityPercentage() {
        return humidityPercentage;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public static HumidityDTO from(Humidity humidity) {
        return new HumidityDTO(humidity.getHumidityPercentage(), humidity.getMeasuredAt());
    }
}
