package com.naeunminchocofarm.ncf_api.iot.controller;

import com.naeunminchocofarm.ncf_api.iot.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.iot.service.TemperatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class TempController {
    private static final Logger log = LogManager.getLogger(TempController.class);
    private final TemperatureService temperatureService;

    public TempController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping("/temperatures")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertTemperature(@RequestParam("temperature") Float temperature, @RequestParam("measured-at")OffsetDateTime measuredAt) {
        // TODO: 로그 찍는 것 제거
        log.info(String.format("temp = %.2f, measuredAt = %s", temperature, measuredAt));
        temperatureService.insertTemperature(temperature, measuredAt);
    }

    @GetMapping("/temperatures")
    public List<AirTemperatureDTO> getAllTemperatures() {
        return temperatureService.getAll();
    }
}
