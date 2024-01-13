package com.koreaIT.develog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.develog.vo.Member;

@Mapper
public interface MemberDao {

	@Select("""
				SELECT * FROM member
				WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
				SELECT * FROM member
				WHERE id = #{memberId}
			""")
	public Member getMemberById(int memberId);
	
}
