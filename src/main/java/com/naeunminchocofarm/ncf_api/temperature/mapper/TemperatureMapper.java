package com.naeunminchocofarm.ncf_api.temperature.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.temperature.dto.AirTemperatureDTO;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

import java.util.List;

@Mapper
public interface TemperatureMapper {
    void insert(@Param("temperatureC") Double temperatureC, @Param("measuredAt") OffsetDateTime measuredAt);
    List<AirTemperature> getRecentTemperaturesGroupedByHour(@Param("pagination") Pagination pagination);
    List<AirTemperature> getRecentTemperatures(@Param("pagination") Pagination pagination);
}
