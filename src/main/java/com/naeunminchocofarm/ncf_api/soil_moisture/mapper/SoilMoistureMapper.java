package com.naeunminchocofarm.ncf_api.soil_moisture.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoisture;
import com.naeunminchocofarm.ncf_api.soil_moisture.entity.SoilMoistureData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface SoilMoistureMapper {
    List<SoilMoisture> getTodaySoilValue(@Param("pagination") Pagination pagination);
    List<SoilMoisture> getRecentSoilGroupedByHour(@Param("pagination") Pagination pagination);

    void insertSoilMoistureValueV2(
            @Param("soilMoistureValue") Integer soilMoistureValue
            , @Param("measuredAt") OffsetDateTime measuredAt
            , @Param("sensorUuid") String sensorUuid
    );

    void insertAll(@Param("datas") List<SoilMoistureData> list);
}


