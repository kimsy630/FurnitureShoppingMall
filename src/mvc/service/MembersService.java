package mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MembersService {
	//중복확인 처리
	public void confirmId(HttpServletRequest req, HttpServletResponse res);
	
	//회원가입 처리
	public void signInAction(HttpServletRequest req, HttpServletResponse res);
	
	//로그인 처리
	public void loginAction(HttpServletRequest req, HttpServletResponse res);
	
	//회원정보 인증및 탈퇴 삭제 처리
	public void deleteMemberAction(HttpServletRequest req, HttpServletResponse res);
	
	//회원정보 인증및 탈퇴 삭제 처리
	public void adminDeleteMemberAction(HttpServletRequest req, HttpServletResponse res);
		
	//회원정보  인증 및 상세 페이지
	public void memberInfo(HttpServletRequest req, HttpServletResponse res);
	
	//회원정보 수정 처리
	public void updateMemberAction(HttpServletRequest req, HttpServletResponse res);
	
	public void memberPwdCheck(HttpServletRequest req, HttpServletResponse res) ;
	
	public void memberList(HttpServletRequest req, HttpServletResponse res) ;
}
