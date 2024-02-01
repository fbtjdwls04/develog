package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.ArticleService;
import com.koreaIT.develog.service.BoardService;
import com.koreaIT.develog.util.Util;
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
		
		boardService.createBoard(rq.getLoginedMemberId(), boardName);
		
		return Util.jsReplace("게시판이 생성되었습니다", Util.f("/usr/article/list?memberId=%d", rq.getLoginedMemberId()));
	}
	
}
