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
	
	public List<Article> getArticles(int memberId, int boardId, int startLimit, int itemsInAPage, String searchCode, String searchMsg) {
		return articleDao.getArticles(memberId, boardId,startLimit,itemsInAPage,searchCode,searchMsg);
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

	public int getArticlesCnt(int memberId, int boardId, String searchCode, String searchMsg) {
		return articleDao.getArticlesCnt(memberId, boardId, searchCode, searchMsg);
	}

	public List<Article> getAllArticles() {
		return articleDao.getAllArticles();
	}

	public void increaseHitCount(int id) {
		articleDao.increaseHitCount(id);
	}

	public Article forPrintArticle(int id) {
		return articleDao.forPrintArticle(id);
	}

	public Article getPrevArticle(int id, int memberId, int boardId) {
		return articleDao.getPrevArticle(id, memberId, boardId);
	}
	
	public Article getNextArticle(int id, int memberId, int boardId) {
		return articleDao.getNextArticle(id, memberId, boardId);
	}
	
}
