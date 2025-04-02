package com.naeunminchocofarm.ncf_api.soil_moisture.controller;

import com.naeunminchocofarm.ncf_api.soil_moisture.service.SoilMoistureService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class SoilMoistureController {
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureController(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @PostMapping("/soil-moisture-values")
    public void insertSoilMoistureValue(@RequestParam("soil-moisture-value") Integer soilMoistureValue, @RequestParam("measured-at")OffsetDateTime measuredAt){
        this.soilMoistureService.insertSoilMoistureValue(soilMoistureValue, measuredAt);
    }
}
