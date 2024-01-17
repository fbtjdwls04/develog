package com.koreaIT.develog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.BoardDao;
import com.koreaIT.develog.vo.Board;

@Service
public class BoardService {
	
	private BoardDao boardDao;
	
	public BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public List<Board> getBoardsByMemberId(int memberId) {
		return boardDao.getBoardsByMemberId(memberId);
	}
	
	
}
