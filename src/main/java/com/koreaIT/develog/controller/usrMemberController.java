package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.develog.service.MemberService;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.ResultData;
import com.koreaIT.develog.vo.Rq;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	private Rq rq;
	
	public UsrMemberController(MemberService memberService, Rq rq) {
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
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		if(Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요");
		}
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		if(Util.empty(nickname)) {
			return Util.jsHistoryBack("닉네임을 입력해주세요");
		}
		if(Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return Util.jsHistoryBack(Util.f("%s은(는) 이미 사용중인 아이디입니다.",loginId));
		}
		
		memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return Util.jsReplace("계정이 생성되었습니다", "/");
	}
	
	@RequestMapping("/usr/member/loginIdDupChk")
	@ResponseBody
	public ResultData loginIdDupChk(String loginId) {
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member != null) {
			return ResultData.from("F-2", "이미 사용중인 아이디입니다");
		}
		
		
		return ResultData.from("S-1", "사용 가능한 아이디");
	}
	
	@RequestMapping("/usr/member/loginInfoCheck")
	@ResponseBody
	public ResultData loginInfoCheck(String loginId, String loginPw) {
		
		if(Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if(Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null || Util.sha256(loginPw).equals(member.getLoginPw()) == false) {
			return ResultData.from("F-3", "아이디 또는 비밀번호를 확인해주세요");
		}
		
		return ResultData.from("S-1", "로그인 성공");
	}
}
