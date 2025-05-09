package com.naeunminchocofarm.ncf_api.smart_farm.mapper;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SimpleFarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.entity.Farm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface FarmMapper {

    // 스마트팜 전체 조회
    List<Farm> getAllFarms();

    //FarmWithMember 조회
    List<Farm> getFarmWithMember();

    // 스마트팜 상세 조회
    Farm getFarmDetailById(@Param("id") Integer id);

    //LoginId로 member_id 조회
    int getMemberIdByLoginId(@Param("loginId") String loginId);

    // 스마트팜 등록
    void insertFarm(Farm farm);

    // 스마트팜 수정
    void updateFarm(Farm farm);

    // 스마트팜 삭제
    void deleteFarm(@Param("id") Integer id);
    List<SimpleFarmDTO> findByMemberId(@Param("memberId") Integer id);
    Optional<SimpleFarmDTO> findByIdAndMemberId(@Param("farmId") Integer farmId, @Param("memberId") Integer memberId);
    Optional<SimpleFarmDTO> findByUuid(@Param("farmUuid") String farmUuid);
    Set<String> findFarmUuidsByMemberId(@Param("memberId") Integer memberId);
}
