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
import com.koreaIT.develog.service.ReplyService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Article;
import com.koreaIT.develog.vo.Board;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.Reply;
import com.koreaIT.develog.vo.Rq;

@Controller
public class UsrArticleController {
	
	private MemberService memberService;
	private ArticleService articleService;
	private BoardService boardService;
	private ReplyService replyService;
	private Rq rq;
	
	public UsrArticleController(
			MemberService memberService
			, ArticleService articleService
			, BoardService boardService 
			, ReplyService replyService
			, Rq rq) {
		
		this.memberService = memberService;
		this.articleService = articleService;
		this.boardService = boardService;
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(
			Model model
			, int memberId
			, @RequestParam(defaultValue = "0") int boardId
			, @RequestParam(defaultValue = "1") int boardPage
			, String searchCode
			, String searchMsg) {
		
		Member member = memberService.getMemberById(memberId);
		List<Board> boards = boardService.getBoardsByMemberId(memberId);
		Board board = boardService.getBoardById(boardId);
		
		if(member == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		
		member.setLoginPw(null);
		
		if(searchMsg != null) {
			searchMsg = Util.cleanText(searchMsg);
		}
		
		int pageSize = 10;
		int itemsInAPage = 15;
		int startLimit = (boardPage-1)*itemsInAPage;
		int articleCnt = articleService.getArticlesCnt(memberId, boardId, searchCode, searchMsg);
		int totalPage = (int) Math.ceil((double) articleCnt / itemsInAPage);
		int beginPage = Util.getBeginPage(boardPage, pageSize);
		int endPage = Util.getEndPage(boardPage, pageSize);
		
		List<Article> articles = articleService.getArticles(memberId, boardId, startLimit, itemsInAPage, searchCode, searchMsg); 
		
		model.addAttribute("member",member);
		model.addAttribute("articles",articles);
		model.addAttribute("boards",boards);
		model.addAttribute("nowBoard",board);
		model.addAttribute("beginPage",beginPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("articleCnt",articleCnt);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("searchCode", searchCode);
		model.addAttribute("searchMsg", searchMsg);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id) {
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		
		Member member = memberService.getMemberById(article.getMemberId());
		
		if(member == null) {
			return rq.jsReturnOnView("잘못된 접근입니다.");
		}
		
		member.setLoginPw(null);
		
		List<Board> boards = boardService.getBoardsByMemberId(article.getMemberId());
		
		Board board = boardService.getBoardById(article.getBoardId());
		List<Reply> replies = replyService.getReplies(id); 
		
		model.addAttribute("member",member);
		model.addAttribute("article",article);
		model.addAttribute("boards",boards);
		model.addAttribute("nowBoard",board);
		model.addAttribute("replies",replies);
		
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
		
		articleService.doWriteArticle(rq.getLoginedMemberId(),boardId, title, body);
		
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
		
		articleService.doModifyArticle(id,boardId, title, body);
		
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
