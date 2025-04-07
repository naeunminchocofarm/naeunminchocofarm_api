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

    @PostMapping("/humidities/v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertHumidity(@RequestParam("humidity-percentage") Double humidityPercentage, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("measured-at") OffsetDateTime measuredAt, @RequestParam("farm-uuid") String farmUuid) {
        log.info(String.format("Humidity: %.2f%%, Measured at: %s, Sensor: %s, Section: %s, Crops: %s, Farm uuid: %s", humidityPercentage, measuredAt, sensorName, sectionName, cropsName, farmUuid));
        humidityService.insertHumidity(farmUuid, cropsName, sectionName, sensorName, humidityPercentage, measuredAt);
    }

    @GetMapping("/humidities")
    public List<HumidityDTO> getAllHumidity() {
        return humidityService.getAllHumidity();
    }
}
