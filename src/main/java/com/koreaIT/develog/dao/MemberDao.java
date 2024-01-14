package com.koreaIT.develog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.develog.vo.Member;

@Mapper
public interface MemberDao {

	@Select("""
				SELECT * FROM `member`
				WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
				SELECT * FROM `member`
				WHERE id = #{memberId}
			""")
	public Member getMemberById(int memberId);

	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW(),
				updateDate = NOW(),
				loginId = #{loginId},
				loginPw = SHA2(#{loginPw},256),
				authLevel = 2,
				`name` = #{name},
				nickname = #{nickname},
				cellphoneNum = #{cellphoneNum},
				email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
}
