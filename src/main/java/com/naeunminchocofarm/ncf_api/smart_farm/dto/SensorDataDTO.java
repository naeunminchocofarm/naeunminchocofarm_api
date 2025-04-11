package com.naeunminchocofarm.ncf_api.smart_farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public class SensorDataDTO {
    private final String name;
    private final Object value;
    @JsonProperty("measured-at")
    private final OffsetDateTime measuredAt;
    private final String sensorUuid;

    public SensorDataDTO(String name, Object value, OffsetDateTime measuredAt, String sensorUuid) {
        this.name = name;
        this.value = value;
        this.measuredAt = measuredAt;
        this.sensorUuid = sensorUuid;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }
}