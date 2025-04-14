package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UuidMapper {

  // UUID 삽입
  void insertUuid(@Param("uuid") String uuid);

  // 마지막 삽입된 UUID의 id 가져오기
  Integer getLastInsertId();
}
