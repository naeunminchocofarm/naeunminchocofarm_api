package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectionMapper {

  // 구역 전체 조회
  List<Section> getAllSections();

  // 특정 farm 구역 상세 조회
  List<Section> getSectionsByFarmId(@Param("farmId") Integer farmId);

  // 구역 등록
  void insertSection(
          @Param("name") String name,
          @Param("farmId") Integer farmId,
          @Param("uuidId") Integer uuidId
  );
}
