package com.koreaIT.develog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.develog.vo.Reply;

@Mapper
public interface ReplyDao {

	@Select("""
			SELECT r.*, m.nickname AS writerName 
			FROM reply AS r
			INNER JOIN member AS m
			WHERE r.memberId = m.id
			AND r.articleId = #{id}
			""")
	List<Reply> getReplies(int id);

	@Insert("""
			INSERT INTO reply
			SET articleId = #{articleId}, 
			memberId = #{memberId},
			regDate = NOW(),
			updateDate = NOW(),
			`body` = #{body};
			""")
	void doWrite(int articleId, int memberId, String body);

	@Select("""
			SELECT * FROM reply
			WHERE id = #{id}
			""")
	Reply getReply(int id);

	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
			""")
	void doDelete(int id);

}
