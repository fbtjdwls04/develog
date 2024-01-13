package com.koreaIT.develog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.Rq;

@Controller
public class usrArticleController {
	
	private MemberService memberService;
	private ArticleService articleService;
	private Rq rq;
	
	public usrArticleController(MemberService memberService, ArticleService articleService, Rq rq) {
		this.memberService = memberService;
		this.articleService = articleService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/article/blogMain")
	public String blogMain(Model model,int memberId) {
		
		Member member = memberService.getMemberById(memberId);
		List<Article> articles = articleService.getArticles(memberId); 
		
		model.addAttribute("member",member);
		model.addAttribute("articles",articles);
		
		return "/usr/article/blogMain";
	}
	
}
