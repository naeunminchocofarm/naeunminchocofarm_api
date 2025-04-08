package com.naeunminchocofarm.ncf_api.soil_moisture.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.soil_moisture.dto.SoilMoistureDTO;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import com.naeunminchocofarm.ncf_api.soil_moisture.service.SoilMoistureService;
import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class SoilMoistureController {
    private static final Logger log = LogManager.getLogger(SoilMoistureController.class);
    private final SoilMoistureService soilMoistureService;

    public SoilMoistureController(SoilMoistureService soilMoistureService) {
        this.soilMoistureService = soilMoistureService;
    }

    @PostMapping("/soil-moistures/v2")
    @Deprecated(since = "/soil-moistures/v3 를 대신 사용하시요.")
    public void insertSoilMoistureValue(@RequestParam("soil-moisture-value") Integer soilMoistureValue, @RequestParam("sensor-name") String sensorName, @RequestParam("section-name") String sectionName, @RequestParam("crops-name") String cropsName, @RequestParam("farm-uuid") String farmUuid, @RequestParam("measured-at") OffsetDateTime measuredAt) {
        log.info(String.format("Soil Moisture: %d, Measured at: %s, Sensor: %s, Section: %s, Crops: %s, Farm uuid: %s", soilMoistureValue, measuredAt, sensorName, sectionName, cropsName, farmUuid));
        soilMoistureService.insertSoilMoistureValue(farmUuid, cropsName, sectionName, sensorName, soilMoistureValue, measuredAt);
    }

    @PostMapping("/soil-moistures/v3")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void insertSoilMoistureValue(
            @RequestParam("soil-moisture-value") Integer soilMoistureValue
            , @RequestParam("measured-at") OffsetDateTime measuredAt
            , @RequestParam("sensor-uuid") String sensorUuid
    ){
        log.info(String.format("Soil Moisture: %d, Measured at: %s, Sensor uuid: %s", soilMoistureValue, measuredAt, sensorUuid));
        soilMoistureService.insertSoilMoistureValue(soilMoistureValue, measuredAt, sensorUuid);
    }

    @GetMapping("/soil-moisture-values")
    //모든 데이터를불러오면 느려지므로 시간당 페이지당 크기로 조절
    public List<SoilMoistureDTO> getTodayValue(@RequestParam(value = "interval", defaultValue = "") String rawInterval
                                    , @RequestParam(value = "page", defaultValue = "1") Integer page
                                    , @RequestParam(value = "size", defaultValue = "1000") Integer size){
        Pagination pagination;

        try {
            pagination = new Pagination(size, page);
        } catch (IllegalArgumentException ex) {
            throw new ApiException(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
            //실패일경우
        }
        return switch (rawInterval) {
            case "1h" -> soilMoistureService.getRecentSoilGroupedByHour(pagination);
            default -> soilMoistureService.getTodayValue(pagination);
        };
    }
}
