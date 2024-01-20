package com.koreaIT.develog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.BoardService;
import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.Board;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.Rq;

@Controller
public class usrArticleController {
	
	private MemberService memberService;
	private ArticleService articleService;
	private BoardService boardService;
	private Rq rq;
	
	public usrArticleController(MemberService memberService, ArticleService articleService,BoardService boardService ,Rq rq) {
		this.memberService = memberService;
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model,int memberId, @RequestParam(defaultValue = "0") int boardId) {
		
		Member member = memberService.getMemberById(memberId);
		List<Article> articles = articleService.getArticlesByMemberIdAndBoardId(memberId, boardId); 
		List<Board> boards = boardService.getBoardsByMemberId(memberId);
		Board board = boardService.getBoardById(boardId);
		
		if(member == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		
		model.addAttribute("nickname",member.getNickname());
		model.addAttribute("memberId",member.getId());
		model.addAttribute("articles",articles);
		model.addAttribute("boards",boards);
		model.addAttribute("board",board);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id, int memberId) {
		
		Member member = memberService.getMemberById(memberId);
		Article article = articleService.getArticleById(id);
		List<Board> boards = boardService.getBoardsByMemberId(memberId);
		
		if(member == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		if(article == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		if(article.getMemberId() != memberId) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		
		Board board = boardService.getBoardById(article.getBoardId());
		
		model.addAttribute("nickname",member.getNickname());
		model.addAttribute("memberId",member.getId());
		model.addAttribute("article",article);
		model.addAttribute("boards",boards);
		model.addAttribute("boardName",board.getName());
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/write")
	public String write(Model model) {
		
		List<Board> boards = boardService.getBoardsByMemberId(rq.getLoginedMemberId());
		model.addAttribute("boards",boards);
		
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String title, String body) {
		
		
		if(Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		title = Util.cleanText(title);
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			return Util.jsHistoryBack("잘못된 접근입니다");
		}
		if(board.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		articleService.doWrite(rq.getLoginedMemberId(),boardId, title, body);
		
		int id = articleService.getMyLastArticleId(rq.getLoginedMemberId());
		
		return Util.jsReplace("글이 작성되었습니다", Util.f("detail?id=%d&memberId=%s", id, rq.getLoginedMemberId()));
	}
	
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return rq.jsReturnOnView("잘못된 접근입니다");
		}
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsReturnOnView("권한이 없습니다");
		}
		
		List<Board> boards = boardService.getBoardsByMemberId(rq.getLoginedMemberId());
		model.addAttribute("boards",boards);
		model.addAttribute("article",article);
		
		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id,int boardId, String title, String body) {
		
		
		if(Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}
		if(Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		title = Util.cleanText(title);
		
		Board board = boardService.getBoardById(boardId);
		
		if(board == null) {
			return Util.jsHistoryBack("잘못된 접근입니다");
		}
		
		if(board.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return Util.jsHistoryBack("잘못된 접근입니다");
		}
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		articleService.doModify(id,boardId, title, body);
		
		return Util.jsReplace("글이 수정되었습니다", Util.f("detail?id=%d&memberId=%s", id, rq.getLoginedMemberId()));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(Model model, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return rq.jsReturnOnView("잘못된 접근입니다");
		}
		
		if(article.getMemberId() != rq.getLoginedMemberId()) {
			return rq.jsReturnOnView("권한이 없습니다");
		}
		
		articleService.doDeleteArticle(id);
		
		return Util.jsReplace("게시글이 삭제되었습니다", Util.f("/usr/article/list?memberId=%d&boardId=%d", article.getMemberId(), article.getBoardId()));
	}
	
}
