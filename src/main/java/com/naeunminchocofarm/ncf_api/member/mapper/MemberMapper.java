package com.naeunminchocofarm.ncf_api.member.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
	public void signUp(Member member);
	public Optional<Member> findByLoginId(@Param("loginId") String loginId);
	public List<Member> getMemberList (@Param("pagination")Pagination pagination);
	public LoginInfo getMemInfo(@Param("id")Integer id);
}
