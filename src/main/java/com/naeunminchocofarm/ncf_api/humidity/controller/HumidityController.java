package com.naeunminchocofarm.ncf_api.humidity.controller;

import com.naeunminchocofarm.ncf_api.humidity.dto.HumidityDTO;
import com.naeunminchocofarm.ncf_api.humidity.service.HumidityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/humidities/v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertHumidity(@RequestParam("humidity-percentage") Double humidityPercentage, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-name") String farmName, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info("humidity-percentage: " + humidityPercentage + ", sensor-name: " + sensorName + ", section-name: " + sectionName + ", crops-name: " + cropsName + ", farm-name: " + farmName + ", measured-at: " + measuredAt);
    }
}
