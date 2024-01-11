package com.koreaIT.develog.service;

import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.MemberDao;
import com.koreaIT.develog.vo.Member;

@Service
public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

}
