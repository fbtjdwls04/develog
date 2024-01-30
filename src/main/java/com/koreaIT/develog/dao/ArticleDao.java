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
				SELECT a.*
					,b.id AS boardId
					,b.name AS boardName
					,(SELECT COUNT(*) FROM reply WHERE articleId = a.id) AS replyCnt 
					,(SELECT COUNT(*) FROM recommendPoint WHERE articleId = a.id) AS `point` 
				FROM board AS b
				INNER JOIN article AS a
				ON b.id = a.boardId
				LEFT JOIN recommendPoint AS rp
				ON a.id = rp.articleId
				LEFT JOIN reply AS r
				ON r.articleId = a.id
				WHERE b.memberId = #{memberId}
				 	<if test='boardId != 0'>
				 		AND b.id = #{boardId}
				 	</if>
				 	<if test='searchCode == "title"'>
				 		AND a.title LIKE CONCAT('%',#{searchMsg},'%')
				 	</if>
				 	<if test='searchCode == "body"'>
				 		AND a.body LIKE CONCAT('%',#{searchMsg},'%')
				 	</if>
				 	<if test='searchCode == "titleOrBody"'>
				 		AND (
					 		a.title LIKE CONCAT('%',#{searchMsg},'%')
					 		OR a.body LIKE CONCAT('%',#{searchMsg},'%')
				 		)
			 		</if>
				GROUP BY a.id
				ORDER BY a.id DESC
				LIMIT #{startLimit}, #{itemsInAPage}

			</script>
			""")
	public List<Article> getArticles(int memberId, int boardId, int startLimit, int itemsInAPage, String searchCode, String searchMsg);

	@Select("""
			SELECT * FROM article
				WHERE id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Select("""
			SELECT a.*
			        , m.nickname AS writerName
			        , IFNULL(SUM(rp.point), 0) AS `point`
				FROM article AS a
				INNER JOIN member AS m
				ON a.memberId = m.id
				LEFT JOIN recommendPoint AS rp
				ON a.id = rp.articleId
				WHERE a.id = #{id} 
				GROUP BY a.id
			""")
	public Article forPrintArticle(int id);

	@Select("""
			SELECT id FROM article
			WHERE memberId = #{memberId}
			ORDER BY id DESC
			limit 1
			""")
	public int getMyLastArticleId(int memberId);

	@Insert("""
			INSERT INTO article
				SET memberId = #{memberId}
				,boardId = #{boardId}
				,regDate = NOW()
				,updateDate = NOW()
				,title = #{title}
				,`body` = #{body}
			""")
	public void doWriteArticle(int memberId, int boardId, String title, String body);

	@Update("""
			UPDATE article
				SET updateDate = NOW()
				,boardId = #{boardId}
				,title = #{title}
				,`body` = #{body}
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
					FROM article AS a
					INNER JOIN board AS b
					ON b.id = a.boardId
					WHERE b.memberId = #{memberId}
					<if test='boardId != 0'>
						AND b.id = #{boardId}
					</if>
					<if test='searchCode == "title"'>
				 		AND a.title LIKE CONCAT('%',#{searchMsg},'%')
				 	</if>
				 	<if test='searchCode == "body"'>
				 		AND a.body LIKE CONCAT('%',#{searchMsg},'%')
				 	</if>
				 	<if test='searchCode == "titleOrBody"'>
				 		AND (
					 		a.title LIKE CONCAT('%',#{searchMsg},'%')
					 		OR a.body LIKE CONCAT('%',#{searchMsg},'%')
				 		)
			 		</if>
			</script>
			""")
	public int getArticlesCnt(int memberId, int boardId, String searchCode, String searchMsg);

	@Select("""
			SELECT * FROM 
				article AS a
				INNER JOIN board AS b
				ON a.boardId = b.id
				ORDER BY a.id DESC
				LIMIT 15
			""")
	public List<Article> getAllArticles();
	
	@Update("""
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			""")
	public void increaseHitCount(int id);

}
