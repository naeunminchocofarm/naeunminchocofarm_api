package com.naeunminchocofarm.ncf_api.humidity.mapper;

import com.naeunminchocofarm.ncf_api.humidity.entity.HumidData;
import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface HumidityMapper {
    List<Humidity> getAllHumidity();
    void insertHumidityV2(
            @Param("humidityPercentage") Double humidityPercentage
            , @Param("measuredAt") OffsetDateTime measuredAt
            , @Param("sensorUuid") String sensorUuid
    );
    void insertAll(@Param("datas") List<HumidData> list);
}


