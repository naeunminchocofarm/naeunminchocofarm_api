package com.naeunminchocofarm.ncf_api.member.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.MemberInfoDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
	void signUp(Member member);
	Optional<Member> findByLoginId(@Param("loginId") String loginId);
	List<Member> getMemberList (@Param("pagination")Pagination pagination);
	Optional<Member> findById(@Param("id") Integer id);
	Optional<MemberInfoDTO> getMemberInfo(@Param("id") Integer id);
}
