package com.naeunminchocofarm.ncf_api.humidity.controller;

import com.naeunminchocofarm.ncf_api.humidity.dto.HumidityDTO;
import com.naeunminchocofarm.ncf_api.humidity.service.HumidityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class HumidityController {
    private final HumidityService humidityService;

    public HumidityController(HumidityService humidityService) {
        this.humidityService = humidityService;
    }

    @GetMapping("/humidities")
    public List<HumidityDTO> getAll(@RequestParam("delta") String delta) {
        return humidityService.getAll(delta);
    }

    @PostMapping("/humidities")
    public void insertHumidity(@RequestParam("value") Float value, @RequestParam("measuredAt") OffsetDateTime measuredAt) {
        humidityService.insert(value, measuredAt);
    }
}
