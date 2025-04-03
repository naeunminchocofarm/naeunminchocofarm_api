package com.naeunminchocofarm.ncf_api.humidity.mapper;

import com.naeunminchocofarm.ncf_api.humidity.dto.HumidityDTO;
import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface HumidityMapper {
    void insert(@Param("humidityPercentage") Double humidityPercentage, @Param("measuredAt") OffsetDateTime measuredAt);

    List<Humidity> getAllHumidity();
}


