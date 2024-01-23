package com.koreaIT.develog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.ReplyDao;
import com.koreaIT.develog.vo.Reply;

@Service
public class ReplyService {
	
	ReplyDao replyDao;
	
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public List<Reply> getReplies(int id) {
		return replyDao.getReplies(id);
	}

	public void doWrite(int articleId, int memberId, String body) {
		replyDao.doWrite(articleId, memberId, body);
	}

	public Reply getReply(int id) {
		return replyDao.getReply(id);
	}

	public void doDelete(int id) {
		replyDao.doDelete(id);
	}

	public void doModify(int id, String body) {
		replyDao.doModify(id, body);
	}
	
	
}
