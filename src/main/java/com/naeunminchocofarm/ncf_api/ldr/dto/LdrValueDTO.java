package com.naeunminchocofarm.ncf_api.ldr.dto;

import java.time.OffsetDateTime;

public class LdrValueDTO {
    private final Integer id;
    private final Integer ldrValue;
    private final OffsetDateTime measuredAt;

    public LdrValueDTO(Integer id, Integer ldrValue, OffsetDateTime measuredAt) {
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
