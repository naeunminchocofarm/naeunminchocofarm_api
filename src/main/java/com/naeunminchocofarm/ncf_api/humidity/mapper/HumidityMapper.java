package com.naeunminchocofarm.ncf_api.humidity.mapper;

import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import org.apache.ibatis.annotations.Mapper;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface HumidityMapper {
    List<Humidity> getAll();
    void insert(Float value, OffsetDateTime measuredAt);
}
