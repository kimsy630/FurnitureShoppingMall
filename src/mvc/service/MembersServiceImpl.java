package mvc.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.MembersDAOImpl;
import mvc.vo.MembersVO;

public class MembersServiceImpl implements MembersService{

	@Override//아이디중복
	public void confirmId(HttpServletRequest req, HttpServletResponse res) { 
		String id=req.getParameter("mb_id");
		req.setAttribute("selectMb", MembersDAOImpl.getInstance().idCheck(id)); 
		req.setAttribute("mb_id",id);
	}

	@Override//회원가입 
	public void signInAction(HttpServletRequest req, HttpServletResponse res) {
		MembersVO vo=new MembersVO();
		vo.setMb_Id(req.getParameter("mb_id"));
		vo.setMb_Pwd(req.getParameter("mb_pwd"));
		vo.setMb_Name(req.getParameter("mb_name"));
		vo.setMb_CertifiNum(req.getParameter("mb_certifiNum"));
		vo.setMb_Phone(req.getParameter("mb_phone"));
		vo.setMb_Email(req.getParameter("mb_email"));
		vo.setMb_Address(req.getParameter("mb_address"));
		vo.setMb_Classifi(req.getParameter("mb_classifi"));
		vo.setMb_join_date(new Timestamp(System.currentTimeMillis()));
		int insertMb=MembersDAOImpl.getInstance().insertClient(vo);
		req.setAttribute("insertMb",insertMb);
	}

	@Override//로그인
	public void loginAction(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().invalidate();
		String mb_id=req.getParameter("mb_id");
		String mb_pwd=req.getParameter("mb_pwd");
		MembersDAOImpl dao=MembersDAOImpl.getInstance();
		int selectMb=dao.idCheck(mb_id);
		if(selectMb==1) {
			String mb_Classifi=dao.idPwdChk(mb_id,mb_pwd);
			if(mb_Classifi.equals("noPwd")) {
				selectMb=-1;
			}else {
				req.getSession().setAttribute("mb_id",mb_id);
				req.getSession().setAttribute("mb_classifi",mb_Classifi);
			}
		}
		req.setAttribute("selectMb",selectMb);
	}

	@Override//탈퇴
	public void deleteMemberAction(HttpServletRequest req, HttpServletResponse res) {
		MembersDAOImpl dao=MembersDAOImpl.getInstance();
		int deleteM=dao.deleteMember((String)req.getSession().getAttribute("mb_id"));
		req.setAttribute("deleteM", deleteM);
	}

	@Override//수정 보이기
	public void memberInfo(HttpServletRequest req, HttpServletResponse res) {
		String strid=(String)req.getSession().getAttribute("mb_id");
		MembersDAOImpl dao=MembersDAOImpl.getInstance();
		MembersVO vo=dao.getMemberInfo(strid);
		req.setAttribute("dto",vo);
	}

	@Override//
	public void updateMemberAction(HttpServletRequest req, HttpServletResponse res) {
		MembersVO vo = new MembersVO();
		vo.setMb_Id((String)req.getSession().getAttribute("mb_id"));
		vo.setMb_Pwd(req.getParameter("mb_pwd"));
		vo.setMb_Address(req.getParameter("mb_pwd"));
		vo.setMb_Phone(req.getParameter("mb_phone"));
		vo.setMb_Email(req.getParameter("mb_email"));
		req.setAttribute("updateMb", MembersDAOImpl.getInstance().updateMember(vo));
	}

	@Override
	public void memberPwdCheck(HttpServletRequest req, HttpServletResponse res)  {
		String mb_id = (String)req.getSession().getAttribute("mb_id");
		String mb_pwd = req.getParameter("mb_pwd");
		req.setAttribute("selectM", MembersDAOImpl.getInstance().PwdChk(mb_id, mb_pwd));
	}

	@Override
	public void memberList(HttpServletRequest req, HttpServletResponse res) {
		List<MembersVO> list = MembersDAOImpl.getInstance().MembersInfo();
		
		req.setAttribute("list", list);
	}

	@Override
	public void adminDeleteMemberAction(HttpServletRequest req, HttpServletResponse res) {
		MembersDAOImpl dao=MembersDAOImpl.getInstance();
		int deleteM=dao.deleteMember(req.getParameter("mb_id"));
		req.setAttribute("deleteM", deleteM);
	}

	

}
