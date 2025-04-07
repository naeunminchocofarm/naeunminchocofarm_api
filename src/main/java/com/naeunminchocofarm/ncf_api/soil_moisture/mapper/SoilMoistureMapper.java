package com.naeunminchocofarm.ncf_api.soil_moisture.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface SoilMoistureMapper {
//    void insert(@Param("soilMoistureValue") Integer soilMoistureValue, @Param("measuredAt") OffsetDateTime measuredAt);
    List<SoilMoisture> getTodaySoilValue(@Param("pagination") Pagination pagination);
    List<SoilMoisture> getRecentSoilGroupedByHour(@Param("pagination") Pagination pagination);
    void insertSoilMoistureValue(@Param("farmUuid") String farmUuid
            , @Param("cropName") String cropName
            , @Param("sectionName") String sectionName
            , @Param("sensorName") String sensorName
            , @Param("soilMoistureValue") Integer soilMoistureValue
            , @Param("measuredAt") OffsetDateTime measuredAt);
}


