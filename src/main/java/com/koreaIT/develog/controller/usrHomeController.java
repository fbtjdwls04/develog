package com.koreaIT.develog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.vo.Article;

@Controller
public class UsrHomeController {
	
	ArticleService articleService;  
	
	public UsrHomeController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/home/main")
	public String home(Model model) {
		
		List<Article> articles = articleService.getAllArticles();
		
		model.addAttribute("articles",articles);
		
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
}
