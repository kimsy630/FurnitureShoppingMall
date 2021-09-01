package mvc.persistence;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.vo.MembersVO;

public interface MembersDAO{
		
	//중복확인 처리
		public int idCheck(String strId);
		
		//회원가입 처리
		public int insertClient(MembersVO vo);
		
		//로그인 처리
		public String idPwdChk(String strId,String strPwd);
		
		//회원정보 삭제 처리
		public int deleteMember(String strId);
		
		//회원정보 상세 페이지
		public MembersVO getMemberInfo(String strId);
		
		//회원정보 수정 처리
		public int updateMember(MembersVO vo);
		
		public void memberPointUpdate(String mb_id,int point);
		
		public void memberBuyPointUpdate(String mb_id, int point);
		
		public List<MembersVO> MembersInfo();
		
		public int PwdChk(String strId, String strPwd);
}
