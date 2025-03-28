package com.naeunminchocofarm.ncf_api.ldr.service;

import com.naeunminchocofarm.ncf_api.ldr.mapper.LdrMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LdrService {
    private final LdrMapper ldrMapper;

    public LdrService(LdrMapper ldrMapper) {
        this.ldrMapper = ldrMapper;
    }

    public void insertLdrValue(Integer ldrValue, OffsetDateTime measuredAt) {
        ldrMapper.insertLdrValue(ldrValue, measuredAt);
    }
}
