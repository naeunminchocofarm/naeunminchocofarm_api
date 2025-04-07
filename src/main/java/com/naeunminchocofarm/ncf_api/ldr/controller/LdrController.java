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

//    @PostMapping("/ldr-values")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void insertLdrValue(@RequestParam("ldr-value") Integer ldrValue, @RequestParam("measured-at")OffsetDateTime measuredAt) {
//        log.info("insert ldr value: value = {}, measured at = {}", ldrValue, measuredAt);
//        ldrService.insertLdrValue(ldrValue, measuredAt);
//    }

    @PostMapping("/sunshines/v2")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertSunShineValue(@RequestParam("sunshine-value") Integer sunshineValue, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-uuid") String farmUuid, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info(String.format("Farm uuid: %s, Crops: %s, Section: %s, Sensor: %s, Sunshine: %d, Measured at: %s", farmUuid, cropsName, sectionName, sensorName, sunshineValue, measuredAt));
//        ldrService.insertLdrValue(sunshineValue, measuredAt);
        ldrService.insertSunshineValue(farmUuid, cropsName, sectionName, sensorName, sunshineValue, measuredAt);
    }

    @GetMapping("/ldr-values")
    public List<LdrValueDTO> getAllLdrValue() {
        return ldrService.getAllLdrValue();
    }
}
