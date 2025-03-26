package com.naeunminchocofarm.ncf_api.temperature.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

@Mapper
public interface TemperatureMapper {
    void insert(@Param("temperatureC") Double temperatureC, @Param("measuredAt") OffsetDateTime measuredAt);
}
