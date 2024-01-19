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
	
	public List<Article> getArticlesByMemberIdAndBoardId(int memberId, int boardId) {
		return articleDao.getArticlesByMemberIdAndBoardId(memberId, boardId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public int getMyLastArticleId(int memberId) {
		return articleDao.getMyLastArticleId(memberId);
	}

	public void doWrite(int memberId, int boardId, String title, String body) {
		articleDao.doWrite(memberId,boardId, title, body);
	}

	public void doModify(int id, int boardId, String title, String body) {
		articleDao.doModify(id,boardId, title, body);
	}
	
}
