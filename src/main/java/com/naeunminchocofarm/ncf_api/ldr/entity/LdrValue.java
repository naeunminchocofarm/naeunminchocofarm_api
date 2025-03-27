package com.naeunminchocofarm.ncf_api.ldr.entity;

import java.time.OffsetDateTime;

public class LdrValue {
    private Integer id;
    private Integer ldrValue;
    private OffsetDateTime measuredAt;

    public LdrValue() {
    }

    public LdrValue(Integer id, Integer ldrValue, OffsetDateTime measuredAt) {
        this.id = id;
        this.ldrValue = ldrValue;
        this.measuredAt = measuredAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLdrValue() {
        return ldrValue;
    }

    public OffsetDateTime getMeasuredAt() {
        return measuredAt;
    }
}
