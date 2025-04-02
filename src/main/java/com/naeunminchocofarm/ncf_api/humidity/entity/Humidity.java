package com.naeunminchocofarm.ncf_api.humidity.entity;

import java.time.OffsetDateTime;

public class Humidity {
    private Integer id;
    private Double humidityPercentage;
    private OffsetDateTime measuredAt;

    public Humidity(Integer id, Double value, OffsetDateTime measuredAt) {
        this.id = id;
        this.humidityPercentage = value;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Double getHumidityPercentage() {
        return humidityPercentage;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }
}
