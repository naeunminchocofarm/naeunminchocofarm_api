package com.naeunminchocofarm.ncf_api.ldr.mapper;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface LdrMapper {
    void insertLdrValue(@Param("ldrValue") Integer ldrValue, @Param("measuredAt") OffsetDateTime measuredAt);

    List<LdrValueDTO> getAllLdrValue();
}
