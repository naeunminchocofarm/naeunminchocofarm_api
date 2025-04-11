package com.naeunminchocofarm.ncf_api.humidity.entity;

import java.time.OffsetDateTime;

public class HumidData {
    public Double value;
    public OffsetDateTime measuredAt;
    public String sensorUuid;

    public HumidData(Double value, OffsetDateTime measuredAt, String sensorUuid) {
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
