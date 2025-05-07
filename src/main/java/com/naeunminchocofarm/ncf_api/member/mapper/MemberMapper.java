package com.naeunminchocofarm.ncf_api.member.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.MemberInfoDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.entity.MemberImg;
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

	// 프로필 이미지 등록
	void insertMemberImg(MemberImg memberImg);

	// 회원 ID로 프로필 이미지 조회
	MemberImg getMemberImgByMemberId(@Param("memberId") Integer memberId);

	// 프로필 이미지 수정
	void updateMemberImg(MemberImg memberImg);

	// 프로필 이미지 삭제
	void deleteMemberImgByMemberId(@Param("memberId") Integer memberId);
}
