package com.naeunminchocofarm.ncf_api.member.mapper;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
	public void signUp(Member member);
	public Member login (Member member);
	public List<Member> getMemberList (@Param("pagination")Pagination pagination);
	public LoginInfo getMemInfo(@Param("id")Integer id);
}
