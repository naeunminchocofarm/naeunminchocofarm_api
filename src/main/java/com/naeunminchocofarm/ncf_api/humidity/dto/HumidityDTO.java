package com.naeunminchocofarm.ncf_api.humidity.dto;

import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;

import java.time.OffsetDateTime;

public class HumidityDTO {
    private final Float value;
    private final OffsetDateTime measuredAt;

    public HumidityDTO(Float value, OffsetDateTime measuredAt) {
        this.value = value;
        this.measuredAt = measuredAt;
    }

    public Float getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public static HumidityDTO from(Humidity humidity) {
        return new HumidityDTO(humidity.getValue(), humidity.getMeasuredAt());
    }
}
