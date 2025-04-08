package com.naeunminchocofarm.ncf_api.ldr.controller;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import com.naeunminchocofarm.ncf_api.ldr.service.LdrService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class LdrController {
    private static final Logger log = LogManager.getLogger(LdrController.class);
    private final LdrService ldrService;

    public LdrController(LdrService ldrService) {
        this.ldrService = ldrService;
    }

    @PostMapping("/sunshines/v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Deprecated(since = "/sunshines/v3를 사용하시오")
    public void insertSunShineValue(@RequestParam("sunshine-value") Integer sunshineValue, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-uuid") String farmUuid, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info(String.format("Sunshine: %d, Measured at: %s, Sensor: %s, Section: %s, Crops: %s, Farm uuid: %s", sunshineValue, measuredAt, sensorName, sectionName, cropsName, farmUuid));
        ldrService.insertSunshineValue(farmUuid, cropsName, sectionName, sensorName, sunshineValue, measuredAt);
    }

    @PostMapping("/sunshines/v3")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertSunshineValue(
            @RequestParam("sunshine-value") Integer sunshineValue
            , @RequestParam("measured-at") OffsetDateTime measuredAt
            , @RequestParam("sensor-uuid") String sensorUuid
    ) {
        log.info(String.format("Sunshine: %d, Measured at: %s, Sensor uuid: %s", sunshineValue, measuredAt, sensorUuid));
        ldrService.insertSunshineValue(sunshineValue, measuredAt, sensorUuid);
    }

    @GetMapping("/ldr-values")
    public List<LdrValueDTO> getAllLdrValue() {
        return ldrService.getAllLdrValue();
    }
}
