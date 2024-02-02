package com.koreaIT.develog.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.koreaIT.develog.vo.RecommendPoint;

@Mapper
public interface RecommendDao {

	@Select("""
			SELECT * FROM recommendPoint
			WHERE articleId = #{articleId}
			AND memberId = #{memberId}
			""")
	RecommendPoint getRecommendPoint(int articleId, int memberId);

	@Delete("""
			DELETE FROM recommendPoint
			WHERE articleId = #{articleId}
			AND memberId = #{memberId}
			""")
	void deleteRecommendPoint(int articleId, int memberId);

	@Insert("""
			INSERT INTO recommendPoint
			SET articleId = #{articleId}
				, memberId = #{memberId}
				, point = 1
			""")
	void insertRecommendPoint(int articleId, int memberId);

	@Select("""
			SELECT COUNT(*) FROM recommendPoint
			WHERE articleId = #{articleId}
			""")
	int getRecommendPointCnt(int articleId);

}
