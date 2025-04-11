package com.naeunminchocofarm.ncf_api.ldr.controller;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import com.naeunminchocofarm.ncf_api.ldr.service.LdrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class LdrController {
    private static final Logger log = LogManager.getLogger(LdrController.class);
    private final LdrService ldrService;

    public LdrController(LdrService ldrService) {
        this.ldrService = ldrService;
    }

    @GetMapping("/ldr-values")
    public List<LdrValueDTO> getAllLdrValue() {
        return ldrService.getAllLdrValue();
    }
}
