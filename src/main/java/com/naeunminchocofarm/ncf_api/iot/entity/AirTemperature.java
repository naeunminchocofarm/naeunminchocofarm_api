package com.naeunminchocofarm.ncf_api.iot.entity;

import java.time.OffsetDateTime;

public class AirTemperature {
    private Integer id;
    private Float temperature;
    private OffsetDateTime measuredAt;

    public AirTemperature(Integer id, Float temperature, OffsetDateTime measuredAt) {
        this.id = id;
        this.temperature = temperature;
        this.measuredAt = measuredAt;
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
