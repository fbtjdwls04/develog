package com.koreaIT.develog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.develog.vo.Board;

@Mapper
public interface BoardDao {
	@Select("""
			SELECT b.*
				,  COUNT(a.id) AS articleCnt
				FROM 
				board AS b
				LEFT JOIN article AS a
				ON a.boardId = b.id
				WHERE b.memberId = #{memberId}
				GROUP BY b.id
		""")
	public List<Board> getBoardsByMemberId(int memberId);

	@Select("""
			SELECT * FROM board
				WHERE id = #{boardId}
		""")
	public Board getBoardById(int boardId);

	@Insert("""
			INSERT INTO board
			SET regDate = NOW()
				, updateDate = NOW()
				, memberId = #{memberId}
				, name = #{boardName}
			""")
	public void createBoard(int memberId, String boardName);

	@Update("""
			UPDATE board
			SET name = #{boardName}
				, updateDate = NOW()
			WHERE id = #{id} 
			""")
	public void boardModify(int id, String boardName);
}
