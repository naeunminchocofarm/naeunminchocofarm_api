package com.naeunminchocofarm.ncf_api.ldr.entity;

import java.time.OffsetDateTime;

public class SunshineData {
    private Integer value;
    private OffsetDateTime measuredAt;
    private String sensorUuid;

    public SunshineData(Integer value, OffsetDateTime measuredAt, String sensorUuid) {
        this.value = value;
        this.measuredAt = measuredAt;
        this.sensorUuid = sensorUuid;
    }

    public Integer getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }
}
