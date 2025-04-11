package com.naeunminchocofarm.ncf_api.temperature.entity;

import java.time.OffsetDateTime;

public class AirTempData {
    private Double value;
    private OffsetDateTime measuredAt;
    private String sensorUuid;

    public AirTempData(Double value, OffsetDateTime measuredAt, String sensorUuid) {
        this.value = value;
        this.measuredAt = measuredAt;
        this.sensorUuid = sensorUuid;
    }

    public Double getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }
}
