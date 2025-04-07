package com.naeunminchocofarm.ncf_api.temperature.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
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
    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping("/air-temperatures/v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertAirTemp(@RequestParam("temperature-c") Double temperatureC, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-uuid") String farmUuid, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info(String.format("Farm uuid: %s, Crops: %s, Section: %s, Sensor: %s, Air Temperature: %.1f'C, Measured at: %s", farmUuid, cropsName, sectionName, sensorName, temperatureC, measuredAt));
        temperatureService.insertAirTemperature(farmUuid, cropsName, sectionName, sensorName, temperatureC, measuredAt);
    }

    //최신데이터 불러오는 중임
    @GetMapping("/temperatures")
    public List<AirTemperatureDTO> getTemperatures(@RequestParam(value = "interval", defaultValue = "") String rawInterval
            , @RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "1000") Integer size) {
        Pagination pagination;

        try {
            pagination = new Pagination(size, page);
        } catch (IllegalArgumentException ex) {
            throw new ApiException(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
        }

        return switch (rawInterval) {
            case "1h" -> temperatureService.getRecentTemperaturesGroupedByHour(pagination);
            default -> temperatureService.getTemperatures(pagination);
        };
    }

}

