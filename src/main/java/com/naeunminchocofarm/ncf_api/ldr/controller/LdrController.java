package com.naeunminchocofarm.ncf_api.ldr.controller;

import com.naeunminchocofarm.ncf_api.ldr.service.LdrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class LdrController {
    private static final Logger log = LogManager.getLogger(LdrController.class);
    private final LdrService ldrService;

    public LdrController(LdrService ldrService) {
        this.ldrService = ldrService;
    }

    @PostMapping("/ldr-values")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertLdrValue(@RequestParam("ldr-value") Integer ldrValue, @RequestParam("measured-at")OffsetDateTime measuredAt) {
        log.info("insert ldr value: value = {}, measured at = {}", ldrValue, measuredAt);
        ldrService.insertLdrValue(ldrValue, measuredAt);
    }
}
