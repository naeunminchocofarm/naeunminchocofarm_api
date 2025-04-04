package com.naeunminchocofarm.ncf_api.soil_moisture.controller;

import com.naeunminchocofarm.ncf_api.soil_moisture.service.SoilMoistureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class SoilMoistureController {
    private static final Logger log = LogManager.getLogger(SoilMoistureController.class);
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureController(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @PostMapping("/soil-moisture-values")
    public void insertSoilMoistureValue(@RequestParam("soil-moisture-value") Integer soilMoistureValue, @RequestParam("measured-at")OffsetDateTime measuredAt){
        log.info("soil moisture value " + soilMoistureValue + ", measured at " + measuredAt);
        this.soilMoistureService.insertSoilMoistureValue(soilMoistureValue, measuredAt);
    }

    @PostMapping("/soil-moistures/v2")
    public void insertSoilMoistureValue(@RequestParam("soil-moisture-value") Integer soilMoistureValue, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-name") String farmName, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info("soil-moisture-value: " + soilMoistureValue + ", sensor-name: " + sensorName + ", section-name: " + sectionName + ", crops-name: " + cropsName + ", farm-name: " + farmName + ", measured-at: " + measuredAt);
    }
}