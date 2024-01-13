package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.Rq;

@Controller
public class usrMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	public usrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/member/login")
	public String login() {
		return "usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member.getLoginPw().equals(Util.sha256(loginPw)) == false) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다");
		}
		
		rq.login(member.getId(), member.getNickname());
		
		return Util.jsReplace(Util.f("%s님 안녕하세요", member.getNickname()),"/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {
		
		rq.logout();
		
		return Util.jsReplace("정상적으로 로그아웃 되었습니다","/");
	}
	
	@RequestMapping("/usr/member/join")
	public String join() {
		return "/usr/member/join";
	}
}
