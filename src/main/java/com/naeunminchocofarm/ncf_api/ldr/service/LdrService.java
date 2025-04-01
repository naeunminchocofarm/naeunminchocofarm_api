package com.naeunminchocofarm.ncf_api.ldr.service;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import com.naeunminchocofarm.ncf_api.ldr.mapper.LdrMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class LdrService {
    private final LdrMapper ldrMapper;

    public LdrService(LdrMapper ldrMapper) {
        this.ldrMapper = ldrMapper;
    }

    public void insertLdrValue(Integer ldrValue, OffsetDateTime measuredAt) {
        ldrMapper.insertLdrValue(ldrValue, measuredAt);
    }

    public List<LdrValueDTO> getAllLdrValue() {
        return ldrMapper.getAllLdrValue();
    }

}
