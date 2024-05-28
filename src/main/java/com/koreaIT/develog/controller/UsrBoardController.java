package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.BoardService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Board;
import com.koreaIT.develog.vo.Rq;

@Controller
public class UsrBoardController {
	
	ArticleService articleService;
	BoardService boardService;
	Rq rq;
	
	public UsrBoardController(ArticleService articleService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.boardService = boardService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/board/create")
	@ResponseBody
	public String create(String boardName) {

		if(Util.empty(boardName)) {
			return Util.jsHistoryBack("게시판 이름을 입력해주세요");
		}
		
		if(boardName.length() > 10) {
			return Util.jsHistoryBack("게시판 이름은 최대 10글자까지 가능합니다");
		}
		
		boardService.createBoard(rq.getLoginedMemberId(), boardName);
		
		return Util.jsReplace("게시판이 생성되었습니다", Util.f("/usr/article/list?memberId=%d", rq.getLoginedMemberId()));
	}
	
	@RequestMapping("/usr/board/doModify")
	@ResponseBody
	public String doModify(int id, String boardName) {
		
		Board board = boardService.getBoardById(id);
		
		if(board == null) {
			return Util.jsHistoryBack("해당 게시판이 존재하지 않습니다");
		}
		
		if(board.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		if(Util.empty(boardName)) {
			return Util.jsHistoryBack("게시판 이름을 입력해주세요");
		}
		
		if(boardName.length() > 20) {
			return Util.jsHistoryBack("게시판 이름은 최대 20글자까지 가능합니다");
		}
		
		boardService.boardModify(id, boardName);
		
		return Util.jsReplace("게시판 이름이 수정되었습니다", Util.f("/usr/article/list?memberId=%d", rq.getLoginedMemberId()));
	}
	
	@RequestMapping("/usr/board/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Board board = boardService.getBoardById(id);
		
		if(board == null) {
			return Util.jsHistoryBack("해당 게시판이 존재하지 않습니다");
		}
		
		if(board.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("권한이 없습니다");
		}
		
		return Util.jsReplace(Util.f("[%s]게시판이 삭제되었습니다", board.getName()), Util.f("/usr/article/list?memberId=%d", rq.getLoginedMemberId()));
	}
	
}
