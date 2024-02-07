package com.koreaIT.develog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.koreaIT.develog.dao.MemberDao;
import com.koreaIT.develog.util.Util;
import com.koreaIT.develog.vo.Member;
import com.koreaIT.develog.vo.ResultData;
import com.koreaIT.develog.service.EmailService;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	private EmailService emailService;
	@Value("${custom.siteName}")
	private String siteName;
	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	
	public MemberService(MemberDao memberDao, EmailService emailService) {
		this.memberDao = memberDao;
		this.emailService = emailService;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int memberId) {
		return memberDao.getMemberById(memberId);
	}

	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		memberDao.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
	}

	public void doModifyIntroduct(int memberId, String itd) {
		memberDao.doModifyIntroduct(memberId, itd);
	}

	public Member getMemberByNameAndEmailAndCell(String name, String email, String cellphoneNum) {
		return memberDao.getMemberByNameAndEmailAndCell(name, email, cellphoneNum);
	}

	public ResultData notifyTempLoginPwByEmail(Member member) {
		String subject = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.createTempPassword();
		String text = "<h1>임시 패스워드 : " + tempPassword + "</h1><br>";
		text += "<a style='display:inline-block;padding:10px;border-radius:10px;border:5px solid black;font-size:4rem;color:inherit;text-decoration:none;' href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";

		ResultData sendRd = emailService.send(member.getEmail(), subject, text);

		if (sendRd.isFail()) {
			return sendRd;
		}

		setTempPassword(member, tempPassword);

		return ResultData.from("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다");
	}
	public void doPwModify(Member member, String password) {
		setTempPassword(member, password);
	}
	private void setTempPassword(Member member, String tempPassword) {
		memberDao.doPasswordModify(member.getId(), tempPassword);
	}

	public void doModify(int id, String name, String nickname, String cellphoneNum, String email) {
		memberDao.doModify(id, name, nickname, cellphoneNum, email);
	}

	public Member getMemberByNickname(String nickname) {
		return memberDao.getMemberByNickname(nickname);
	}

}
