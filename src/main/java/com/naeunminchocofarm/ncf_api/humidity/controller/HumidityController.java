package com.naeunminchocofarm.ncf_api.humidity.controller;

import com.naeunminchocofarm.ncf_api.humidity.dto.HumidityDTO;
import com.naeunminchocofarm.ncf_api.humidity.service.HumidityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class HumidityController {
    private static final Logger log = LogManager.getLogger(HumidityController.class);
    private final HumidityService humidityService;

    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @PostMapping("/humidities")
    public void insertHumidity(@RequestParam("humidity-percentage") Double humidityPercentage, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info("humidity " + humidityPercentage + "%, measured at " + measuredAt);
        humidityService.insert(humidityPercentage, measuredAt);
    }
}
