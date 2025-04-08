package com.naeunminchocofarm.ncf_api.ldr.mapper;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface LdrMapper {
    List<LdrValueDTO> getAllLdrValue();

    @Deprecated()
    void insertSunshineValue(@Param("farmUuid") String farmUuid
            , @Param("cropName") String cropName
            , @Param("sectionName") String sectionName
            , @Param("sensorName") String sensorName
            , @Param("sunshineValue") Integer sunshineValue
            , @Param("measuredAt") OffsetDateTime measuredAt);

    void insertSunshineValueV2(
            @Param("sunshineValue") Integer sunshineValue
            , @Param("measuredAt") OffsetDateTime measuredAt
            , @Param("sensorUuid") String sensorUuid
    );
}
