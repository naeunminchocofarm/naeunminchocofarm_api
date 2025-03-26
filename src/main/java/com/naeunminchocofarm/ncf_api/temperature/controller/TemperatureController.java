package com.naeunminchocofarm.ncf_api.temperature.controller;

import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.temperature.service.TemperatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class TemperatureController {
    private static final Logger log = LogManager.getLogger(TemperatureController.class);
    private TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping("/temperatures")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertTemperature(@RequestParam("temperature") Double temperature, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info(String.format("temperature = %.2f, measuredAt = %s", temperature, measuredAt));
        temperatureService.insertTemperature(temperature, measuredAt);
    }

    @GetMapping("/temperatures")
    public List<AirTemperatureDTO> getAllTemperatures() {
        return List.of();
    }
}
