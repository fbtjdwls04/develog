package com.koreaIT.develog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.ArticleDao;
import com.koreaIT.develog.vo.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public List<Article> getArticles(int memberId, int boardId, int startLimit, int itemsInAPage) {
		return articleDao.getArticles(memberId, boardId,startLimit,itemsInAPage);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int getMyLastArticleId(int memberId) {
		return articleDao.getMyLastArticleId(memberId);
	}

	public void doWriteArticle(int memberId, int boardId, String title, String body) {
		articleDao.doWriteArticle(memberId,boardId, title, body);
	}

	public void doModifyArticle(int id, int boardId, String title, String body) {
		articleDao.doModifyArticle(id,boardId, title, body);
	}

	public void doDeleteArticle(int id) {
		articleDao.doDeleteArticle(id);
	}

	public int getArticlesCnt(int memberId, int boardId) {
		return articleDao.getArticlesCnt(memberId, boardId);
	}
	
}
