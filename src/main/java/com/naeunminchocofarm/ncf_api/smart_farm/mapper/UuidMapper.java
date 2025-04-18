package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UuidMapper {

  // UUID 삽입
  void insertUuid(@Param("uuid") String uuid);

}
