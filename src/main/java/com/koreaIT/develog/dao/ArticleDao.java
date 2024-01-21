package com.koreaIT.develog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.koreaIT.develog.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Select("""
			<script>
				SELECT a.*, 
						b.id AS boardId,
						b.name AS boardName
				FROM board AS b 
				INNER JOIN article AS a 
				WHERE b.id = a.boardId
				AND a.memberId = #{memberId}
				<if test='boardId != 0'>
					AND a.boardId = #{boardId}
				</if>
				ORDER BY a.id DESC
				LIMIT #{startLimit}, #{itemsInAPage}
				
			</script>
			""")
	public List<Article> getArticles(int memberId, int boardId, int startLimit, int itemsInAPage);

	@Select("""
			SELECT * FROM article
			WHERE id = #{id}
		""")
	Article getArticleById(int id);

	@Select("""
			SELECT id FROM article
			WHERE memberId = #{memberId}
			ORDER BY id DESC
			limit 1
			""")
	public int getMyLastArticleId(int memberId);

	@Insert("""
			INSERT INTO article
			SET memberId = #{memberId},
			boardId = #{boardId},
			regDate = NOW(),
			updateDate = NOW(),
			title = #{title},
			`body` = #{body}
			""")
	public void doWriteArticle(int memberId, int boardId, String title, String body);

	@Update("""
			UPDATE article
			SET updateDate = NOW(),
			boardId = #{boardId},
			title = #{title},
			`body` = #{body}
			WHERE id = #{id}
			""")
	public void doModifyArticle(int id, int boardId, String title, String body);

	@Delete("""
			DELETE FROM article
			WHERE id = #{id}
			""")
	public void doDeleteArticle(int id);

	@Select("""
			<script>
				SELECT COUNT(*)
				FROM article
				WHERE memberId = #{memberId}
				<if test='boardId != 0'>
					AND boardId = #{boardId}
				</if>
			</script>
			""")
	public int getArticlesCnt(int memberId, int boardId);

}
