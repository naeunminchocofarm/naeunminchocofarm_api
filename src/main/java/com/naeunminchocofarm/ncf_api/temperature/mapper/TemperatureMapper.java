package com.naeunminchocofarm.ncf_api.temperature.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTempData;
import com.naeunminchocofarm.ncf_api.temperature.entity.AirTemperature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

import java.util.List;
import java.util.stream.Stream;

@Mapper
public interface TemperatureMapper {
    //그룹변 시간평균?
    List<AirTemperature> getRecentTemperaturesGroupedByHour(@Param("pagination") Pagination pagination);
    //최근들 받아온 날짜에
    List<AirTemperature> getRecentTemperatures(@Param("pagination") Pagination pagination);
    //최근하나
    AirTemperature getRecentOneTemp();
    void insertAirTemperatureV2(
            @Param("temperatureC") Double temperatureC
            , @Param("measuredAt") OffsetDateTime measuredAt
            , @Param("sensorUuid") String sensorUuid);

    void insertAll(@Param("datas") List<AirTempData> list);
}
