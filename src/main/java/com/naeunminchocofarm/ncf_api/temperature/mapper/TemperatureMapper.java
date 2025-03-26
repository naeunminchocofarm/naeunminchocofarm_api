package com.naeunminchocofarm.ncf_api.temperature.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import com.naeunminchocofarm.ncf_api.temperature.dto.TemperatureDTO;

import java.util.List;

@Mapper
public interface TemperatureMapper {
    void insert(@Param("temperatureC") Double temperatureC, @Param("measuredAt") OffsetDateTime measuredAt);
	int getNowTemp();
	List<TemperatureDTO> getTodayTemp();
}
