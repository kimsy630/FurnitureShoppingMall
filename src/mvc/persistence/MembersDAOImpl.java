package mvc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import mvc.vo.MembersVO;

public class MembersDAOImpl implements MembersDAO{
	private static MembersDAOImpl instance=null;
	
	DataSource dataSource;
	private MembersDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	
	public static MembersDAOImpl getInstance() {
		if(instance==null)instance=new MembersDAOImpl();
		return instance;
	}
	
	@Override
	public int idCheck(String strId) {//아이디 체크
		int selectMb=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT mb_id FROM members WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				selectMb=1;
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectMb;
	}

	@Override
	public int insertClient(MembersVO vo) {//가입
		int selectMb=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="INSERT INTO members(mb_id ,mb_pwd ,mb_name, mb_certifiNum, mb_phone, mb_email, mb_address, mb_join_date,mb_classifi) VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getMb_Id());
			pstmt.setString(2,vo.getMb_Pwd());
			pstmt.setString(3,vo.getMb_Name());
			pstmt.setString(4,vo.getMb_CertifiNum());
			pstmt.setString(5,vo.getMb_Phone());
			pstmt.setString(6,vo.getMb_Email());
			pstmt.setString(7,vo.getMb_Address());
			pstmt.setTimestamp(8, vo.getMb_join_date());
			pstmt.setString(9, vo.getMb_Classifi());
			selectMb=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectMb;
	}

	@Override
	public String idPwdChk(String strId, String strPwd){//아이디 비밀번호 체크
		String mb_classifi="noId";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM members WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mb_classifi="noPwd";
				if(strPwd.equals(rs.getString("mb_pwd"))) {
					mb_classifi =rs.getString("mb_classifi");
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mb_classifi;
	}

	@Override
	public int deleteMember(String strId) {//회원 탈퇴 안지우고 탈퇴날짜만
		int deleteMb=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="DELETE members WHERE mb_id=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			deleteMb = pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return deleteMb;
	}

	@Override
	public MembersVO getMemberInfo(String strId) {//회원정보 수정할때 사용
		MembersVO vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM members WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new MembersVO();
				vo.setMb_Id(rs.getString("mb_Id"));
				vo.setMb_Pwd(rs.getString("mb_Pwd"));
				vo.setMb_Name(rs.getString("mb_Name"));
				vo.setMb_CertifiNum(rs.getString("mb_CertifiNum"));
				vo.setMb_Phone(rs.getString("mb_Phone"));
				vo.setMb_Email(rs.getString("mb_Email"));
				vo.setMb_Address(rs.getString("mb_Address"));
				vo.setMb_join_date(rs.getTimestamp("mb_join_date"));
				vo.setMb_Withdraw_date(rs.getTimestamp("mb_Withdraw_date"));
				vo.setMb_Rank_Point(rs.getInt("mb_Rank_Point"));
				vo.setMb_Point(rs.getInt("mb_Point"));
				vo.setMb_Classifi(rs.getString("mb_Classifi"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo;
	}

	@Override
	public int updateMember(MembersVO vo) {//회원정보 수정
		int updateMb=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE members SET mb_pwd=?, mb_phone=?,mb_email=?,mb_address=?  WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getMb_Pwd());
			pstmt.setString(2,vo.getMb_Phone());
			pstmt.setString(3,vo.getMb_Email());
			pstmt.setString(4,vo.getMb_Address());
			pstmt.setString(5, vo.getMb_Id());
			updateMb=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updateMb;
	}
	
	@Override
	public void memberPointUpdate(String mb_id,int point) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE members SET mb_point=mb_point+?  WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2,mb_id);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void memberBuyPointUpdate(String mb_id, int point){
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE members SET mb_point=mb_point+?,mb_rank_point=mb_rank_point+?  WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setInt(2, point);
			pstmt.setString(3,mb_id);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<MembersVO> MembersInfo() {
		List<MembersVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM members WHERE mb_Classifi!='관리자' ";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<MembersVO>();
				do {
					MembersVO vo=new MembersVO();
					vo.setMb_Id(rs.getString("mb_Id"));
					vo.setMb_Pwd(rs.getString("mb_Pwd"));
					vo.setMb_Name(rs.getString("mb_Name"));
					vo.setMb_CertifiNum(rs.getString("mb_CertifiNum"));
					vo.setMb_Phone(rs.getString("mb_Phone"));
					vo.setMb_Email(rs.getString("mb_Email"));
					vo.setMb_Address(rs.getString("mb_Address"));
					vo.setMb_join_date(rs.getTimestamp("mb_join_date"));
					vo.setMb_Withdraw_date(rs.getTimestamp("mb_Withdraw_date"));
					vo.setMb_Rank_Point(rs.getInt("mb_Rank_Point"));
					vo.setMb_Point(rs.getInt("mb_Point"));
					vo.setMb_Classifi(rs.getString("mb_Classifi"));
					dto.add(vo);
				}while(rs.next());
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Override
	public int PwdChk(String strId, String strPwd) {
		int selectM = 0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT COUNT(*) cnt FROM members WHERE mb_id=? AND mb_pwd = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			pstmt.setString(2,strPwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				selectM = rs.getInt("cnt");
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectM;
	};
}
