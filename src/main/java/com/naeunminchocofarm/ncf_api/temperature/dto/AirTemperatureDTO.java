package com.naeunminchocofarm.ncf_api.temperature.dto;

import java.time.OffsetDateTime;

public class AirTemperatureDTO {
    private final Integer id;
    private final Double value;
    private final OffsetDateTime measuredAt;

    public AirTemperatureDTO(Integer id, Double value, OffsetDateTime measuredAt) {
        this.id = id;
        this.value = value;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }
}
