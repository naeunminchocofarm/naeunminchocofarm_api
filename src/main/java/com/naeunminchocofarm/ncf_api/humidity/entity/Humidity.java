package com.naeunminchocofarm.ncf_api.humidity.entity;

import java.time.OffsetDateTime;

public class Humidity {
    private Integer id;
    private Float value;
    private OffsetDateTime measuredAt;

    public Humidity(Integer id, Float value, OffsetDateTime measuredAt) {
        this.id = id;
        this.value = value;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Float getValue() {
        return value;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }
}
