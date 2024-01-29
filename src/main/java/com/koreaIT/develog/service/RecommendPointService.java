package com.koreaIT.develog.service;

import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.RecommendDao;
import com.koreaIT.develog.vo.RecommendPoint;

@Service
public class RecommendPointService {
	
	private RecommendDao recommendDao;
	
	public RecommendPointService(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	public RecommendPoint getRecommendPoint(int articleId, int memberId) {
		return recommendDao.getRecommendPoint(articleId, memberId);
	}

	public void deleteRecommendPoint(int articleId, int memberId) {
		recommendDao.deleteRecommendPoint(articleId, memberId);
	}

	public void insertRecommendPoint(int articleId, int memberId) {
		recommendDao.insertRecommendPoint(articleId, memberId);
	}
	
}
