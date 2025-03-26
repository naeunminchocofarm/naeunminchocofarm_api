package com.naeunminchocofarm.ncf_api.iot.mapper;

import com.naeunminchocofarm.ncf_api.iot.entity.AirTemperature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Mapper
public interface TemperatureMapper {
    void insert(@Param("temperature") Float temperature, @Param("measuredAt") OffsetDateTime measuredAt);
    List<AirTemperature> getAll();
}
