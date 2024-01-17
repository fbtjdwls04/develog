package com.koreaIT.develog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.develog.vo.Article;

@Mapper
public interface ArticleDao {
	
	@Select("""
				SELECT * FROM article
				WHERE memberId = #{memberId}
			""")
	List<Article> getArticlesByMemberId(int memberId);

	@Select("""
			SELECT * FROM article
			WHERE id = #{id}
		""")
	Article getArticleById(int id);

}
