package com.koreaIT.develog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.vo.Article;

@Controller
public class UsrHomeController {
	
	private ArticleService articleService;  
	@Value("${custom.kakaoApiKey}")
	private String kakaoApiKey;
	
	public UsrHomeController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/home/main")
	public String home(Model model) {
		
		List<Article> articles = articleService.getAllArticles();
		
		model.addAttribute("articles",articles);
		model.addAttribute("kakaoApiKey", kakaoApiKey);
		
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@RequestMapping("/usr/home/test")
	public String test() {
		return "/usr/home/test";
	}
}
