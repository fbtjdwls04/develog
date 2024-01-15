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
	
	public List<Article> getArticles(int memberId) {
		return articleDao.getArticles(memberId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}
	
}
