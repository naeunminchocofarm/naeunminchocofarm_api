package com.naeunminchocofarm.ncf_api.temperature.entity;

import java.time.OffsetDateTime;

public class AirTemperature {
    private Integer id;
    private Double temperatureC;
    private OffsetDateTime measuredAt;

    public AirTemperature() {
    }

    public AirTemperature(Integer id, Double temperatureC, OffsetDateTime measuredAt) {
        this.id = id;
        this.temperatureC = temperatureC;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Double getTemperatureC() {
        return temperatureC;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTemperatureC(Double temperatureC) {
        this.temperatureC = temperatureC;
    }

    public void setMeasuredAt(OffsetDateTime measuredAt) {
        this.measuredAt = measuredAt;
    }
}
