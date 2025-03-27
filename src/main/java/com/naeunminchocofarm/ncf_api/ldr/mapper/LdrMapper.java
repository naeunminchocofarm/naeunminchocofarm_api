package com.naeunminchocofarm.ncf_api.ldr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;

@Mapper
public interface LdrMapper {
    void insertLdrValue(@Param("ldrValue") Integer ldrValue, @Param("measuredAt") OffsetDateTime measuredAt);
}
