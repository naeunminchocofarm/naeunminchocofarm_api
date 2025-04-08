package com.naeunminchocofarm.ncf_api.temperature.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

import java.util.List;

@Mapper
public interface TemperatureMapper {
    //그룹변 시간평균?
    List<AirTemperature> getRecentTemperaturesGroupedByHour(@Param("pagination") Pagination pagination);
    //최근들 받아온 날짜에
    List<AirTemperature> getRecentTemperatures(@Param("pagination") Pagination pagination);
    //최근하나
    AirTemperature getRecentOneTemp();

    @Deprecated
    void insertAirTemperature(@Param("farmUuid") String farmUuid
            , @Param("cropsName") String cropsName
            , @Param("sectionName") String sectionName
            , @Param("sensorName") String sensorName
            , @Param("temperatureC") Double temperatureC
            , @Param("measuredAt") OffsetDateTime measuredAt);

    void insertAirTemperatureV2(
            @Param("temperatureC") Double temperatureC
            , @Param("measuredAt") OffsetDateTime measuredAt
            , @Param("sensorUuid") String sensorUuid);
}
