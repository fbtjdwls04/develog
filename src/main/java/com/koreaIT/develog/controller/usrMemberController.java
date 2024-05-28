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
		
		return Util.jsReplace(Util.f("%s[%s]님 안녕하세요", member.getLoginId(), member.getNickname()),"/");
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
		cellphoneNum = Util.cleanText(cellphoneNum).replaceAll("-", "");
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
	
	@RequestMapping("/usr/member/nicknameDupChk")
	@ResponseBody
	public ResultData nicknameDupChk(String nickname) {
		
		
		if(Util.empty(nickname)) {
			return ResultData.from("F-1", "닉네임을 입력해주세요");
		}
		
		nickname = Util.cleanText(nickname);
		
		Member member = memberService.getMemberByNickname(nickname);
		
		if(member != null) {
			return ResultData.from("F-2", "이미 사용중인 닉네임입니다");
		}
		
		return ResultData.from("S-1", "사용 가능한 닉네임");
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
	public String doModify(String name, String nickname, String cellphoneNum, String email) {
		
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		name = Util.cleanText(name);
		nickname = Util.cleanText(nickname);
		cellphoneNum = Util.cleanText(cellphoneNum).replaceAll("-", "");
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
		
		memberService.doModify(rq.getLoginedMemberId(),name, nickname, cellphoneNum, email);
		
		return Util.jsReplace("정보가 수정되었습니다", "myPage");
	}
	
	@RequestMapping("/usr/member/pwModify")
	public String pwModify() {
		
		return "/usr/member/pwModify";
	}
	
	@RequestMapping("/usr/member/doPwModify")
	@ResponseBody
	public String doPwModify(String password, String pwChk) {
		
		Member member = memberService.getMemberById(rq.getLoginedMemberId());
		
		if(password.equals(pwChk) == false) {
			return Util.jsHistoryBack("비밀번호와 비밀번호 확인이 일치하지 않습니다");
		}
		
		if(member.getLoginPw().equals(Util.sha256(password))) {
			return Util.jsHistoryBack("현재 비밀번호와 동일합니다");
		}
		
		memberService.doPwModify(member,password);
		
		return Util.jsReplace("비밀번호가 변경되었습니다", "/");
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
//	계정 찾기
	
	@RequestMapping("/usr/member/findLoginId")
	public String findLoginId() {
		
		return "/usr/member/findLoginId";
	}
	@RequestMapping("/usr/member/findLoginPw")
	public String findLoginPw() {
		return "/usr/member/findLoginPw";
	}
	
	@RequestMapping("/usr/member/doFindLoginId")
	@ResponseBody
	public String doFindLoginId(String name, String email, String cellphoneNum) {
		
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		if(Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByNameAndEmailAndCell(name, email, cellphoneNum);
		
		if(member == null) {
			return Util.jsHistoryBack("입력하신 정보와 일치하는 회원이 없습니다");
		}
		
		return Util.jsReplace(Util.f("회원님의 아이디는 [ %s ] 입니다", member.getLoginId()), "login");
	}
	
	@RequestMapping("/usr/member/doFindLoginPw")
	@ResponseBody
	public String doFindLoginPw(String loginId, String name, String email, String cellphoneNum) {
		
		if(Util.empty(loginId)) {
			return Util.jsHistoryBack("아이디를 입력해주세요");
		}
		
		if(Util.empty(name)) {
			return Util.jsHistoryBack("이름을 입력해주세요");
		}
		
		if(Util.empty(email)) {
			return Util.jsHistoryBack("이메일을 입력해주세요");
		}
		
		if(Util.empty(cellphoneNum)) {
			return Util.jsHistoryBack("전화번호를 입력해주세요");
		}
		
		Member member = memberService.getMemberByLoginId(loginId);

		if(member == null) {
			return Util.jsHistoryBack("입력하신 정보와 일치하는 회원이 없습니다");
		}
		
		if(member.getName().equals(name) == false) {
			return Util.jsHistoryBack("이름이 일치하지 않습니다");
		}
		
		if(member.getEmail().equals(email) == false) {
			return Util.jsHistoryBack("이메일이 일치하지 않습니다");
		}
		
		if(member.getCellphoneNum().equals(cellphoneNum) == false) {
			return Util.jsHistoryBack("전화번호가 일치하지 않습니다");
		}
		
		ResultData notifyTempLoginPwByEmailRd = memberService.notifyTempLoginPwByEmail(member);

		return Util.jsReplace(notifyTempLoginPwByEmailRd.getMsg(), "login");
	}
}
