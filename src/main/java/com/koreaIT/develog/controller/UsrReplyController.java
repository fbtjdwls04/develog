package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.BoardService;
import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.service.ReplyService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.Reply;
import com.koreaIT.develog.vo.Rq;

@Controller
public class UsrReplyController {
	
	private MemberService memberService;
	private ArticleService articleService;
	private BoardService boardService;
	private ReplyService replyService;
	private Rq rq;
	
	public UsrReplyController(MemberService memberService, ArticleService articleService,BoardService boardService ,ReplyService replyService,Rq rq) {
		this.memberService = memberService;
		this.articleService = articleService;
		this.boardService = boardService;
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(int articleId, String body) {
		
		Article article = articleService.getArticleById(articleId);
		
		if(article == null) {
			return Util.jsHistoryBack("잘못된 접근입니다");
		}
		
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		body = Util.cleanText(body);
		
		replyService.doWrite(articleId, rq.getLoginedMemberId(),body);
		
		return Util.jsNotAlertReplace(Util.f("/usr/article/detail?id=%d", articleId));
	}	
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Reply reply = replyService.getReply(id);
		
		if(reply == null) {
			Util.jsHistoryBack("잘못된 접근입니다");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) {
			Util.jsHistoryBack("권한이 없습니다");
		}
		
		replyService.doDelete(id);
		
		return Util.jsReplace("댓글이 삭제되었습니다", Util.f("/usr/article/detail?id=%d", reply.getArticleId()));
	}	
	
	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body) {
		
		Reply reply = replyService.getReply(id);
		
		if(reply == null) {
			return Util.jsHistoryBack("잘못된 접근입니다");
		}
		
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		if(reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		body = Util.cleanText(body);
		
		replyService.doModify(id, body);
		
		return Util.jsReplace("댓글이 수정되었습니다", Util.f("/usr/article/detail?id=%d", reply.getArticleId()));
	}	
}
