package com.naeunminchocofarm.ncf_api.soil_moisture.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

@Mapper
public interface SoilMoistureMapper {
    void insert(@Param("soilMoistureValue") Integer soilMoistureValue, @Param("measuredAt") OffsetDateTime measuredAt);
}
