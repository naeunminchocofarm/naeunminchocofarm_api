package com.naeunminchocofarm.ncf_api.ldr.mapper;

import com.naeunminchocofarm.ncf_api.ldr.dto.LdrValueDTO;
import com.naeunminchocofarm.ncf_api.ldr.entity.SunshineData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface LdrMapper {
    List<LdrValueDTO> getAllLdrValue();
    void insertAll(@Param("datas") List<SunshineData> list);
}
