package com.koreaIT.develog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		loginId = Util.cleanText(loginId);
		loginPw = Util.cleanText(loginPw);
		
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
		
		loginId = Util.cleanText(loginId);
		loginPw = Util.cleanText(loginPw);
		name = Util.cleanText(name);
		nickname = Util.cleanText(nickname);
		cellphoneNum = Util.cleanText(cellphoneNum);
		email = Util.cleanText(email);
		
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
		
		loginId = Util.cleanText(loginId);
		
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
		
		loginId = Util.cleanText(loginId);
		loginPw = Util.cleanText(loginPw);
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null || Util.sha256(loginPw).equals(member.getLoginPw()) == false) {
			return ResultData.from("F-3", "아이디 또는 비밀번호를 확인해주세요");
		}
		
		return ResultData.from("S-1", "로그인 성공");
	}
	
	@RequestMapping("/usr/member/myPage")
	public String myPage(Model model) {
		
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		model.addAttribute("member", member);
		
		return "/usr/member/myPage";
	}
	
	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		loginPw = Util.sha256(Util.cleanText(loginPw));
		
		if(member.getLoginPw().equals(loginPw) == false) {
			return Util.jsHistoryBack("비밀번호를 확인해주세요");
		}
		
		name = Util.cleanText(name);
		nickname = Util.cleanText(nickname);
		cellphoneNum = Util.cleanText(cellphoneNum);
		email = Util.cleanText(email);
		
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
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		
		
		return Util.jsReplace("정보가 수정되었습니다", "myPage");
	}
	
	@RequestMapping("/usr/member/doModifyIntroduct")
	@ResponseBody
	public String doModifyIntroduct(String itd) {
		
		if(Util.empty(itd)) {
			return Util.jsHistoryBack("소개글을 입력해주세요");
		}
		
		if(itd.length() > 100) {
			return Util.jsHistoryBack("소개글은 100자 이하만 가능합니다");
		}
		
		memberService.doModifyIntroduct(rq.getLoginedMemberId(), itd);
		
		return Util.jsNotAlertReplace(Util.f("/usr/article/list?memberId=%d", rq.getLoginedMemberId()));
	}
	
	@RequestMapping("/usr/member/findLoginId")
	public String findLoginId() {
		
		return "/usr/member/findLoginId";
	}
	
	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(String name, String email) {
		
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		return Util.jsNotAlertReplace("/");
	}
}
