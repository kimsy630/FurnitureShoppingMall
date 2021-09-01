package mvc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.vo.SearchVO;
import mvc.vo.BoardVO;

public class BoardDAOImpl implements BoardDAO{
	private static BoardDAOImpl instance=null;
	
	DataSource dataSource;
	private BoardDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	
	public static BoardDAOImpl getInstance() {
		if(instance==null)instance=new BoardDAOImpl();
		return instance;
	}
	
	@Override
	public int getBoardCnt(SearchVO search) {
		int selectCnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT COUNT(*) as cnt "+
			            "FROM ( SELECT b_ref "
			            + "       FROM board ";
			if(search.getType()==1) {//내용 제목
				sql+=" WHERE b_content LIKE '%"+search.getSearch()+"%' " + 
						"  OR b_subject LIKE '%"+search.getSearch()+"%' ";
	        }else if(search.getType()==2) {//작성자
				sql+=" WHERE b_writer LIKE '%"+search.getSearch()+"%' ";
	        }
		    sql+="  GROUP BY b_ref)";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				selectCnt=rs.getInt("cnt");
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return selectCnt;
	}

	@Override
	public ArrayList<BoardVO> getBoardList(SearchVO search) {
		ArrayList<BoardVO> dtos =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM board " + 
					"WHERE b_ref in (SELECT b_ref " + 
					"                FROM(SELECT b_ref , ROWNUM as rnum " + 
					"                     FROM (SELECT b_ref " + 
					"                            FROM board " ;
			if(search.getType()==1) {//내용 제목
				sql+="                            WHERE b_id in (SELECT b_ref " + 
					"                                              FROM board " + 
					"                                             WHERE b_content LIKE '%"+ search.getSearch()+"%' "+
					"											     OR b_subject LIKE '%"+ search.getSearch()+"%')  ";
			}else if(search.getType()==2) {//내용 제목
				sql+="                            WHERE b_id in (SELECT b_ref " + 
					"                                              FROM board " + 
					"                                             WHERE b_writer LIKE '%"+ search.getSearch()+"%')";
			}
			sql+="                            GROUP BY b_ref " + 
					"                            ORDER BY b_ref DESC)) " + 
					"                WHERE rNum>=? AND rNum<=?) " + 
					"ORDER BY  b_ref DESC,b_ref_step ASC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, search.getStart());
			pstmt.setInt(2, search.getEnd());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dtos=new ArrayList<BoardVO>();
				do {
					BoardVO vo=new BoardVO();
					vo.setB_id(rs.getInt("b_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setB_writer(rs.getString("b_writer"));
					vo.setB_subject(rs.getString("b_subject"));
					vo.setB_content(rs.getString("b_content"));
					vo.setB_ref(rs.getInt("b_ref"));
					vo.setB_ref_step(rs.getInt("b_ref_step"));
					vo.setB_ref_level(rs.getInt("b_ref_level"));
					vo.setB_reg_date(rs.getTimestamp("b_reg_date"));
					
					if(dtos.size()>0&&dtos.get(dtos.size()-1).getB_ref()==vo.getB_ref()) {
						if(dtos.get(dtos.size()-1).getReply()==null) {
							dtos.get(dtos.size()-1).setReply(new ArrayList<BoardVO>());
						}
						dtos.get(dtos.size()-1).getReply().add(vo);
					}else {
						dtos.add(vo);
					}
				}while(rs.next());
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return dtos;
	}

	@Override
	public BoardVO getBoardDetail(int b_id) {
		BoardVO vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM board WHERE b_id=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
				vo.setB_id(rs.getInt("b_id"));
				vo.setMb_id(rs.getString("mb_id"));
				vo.setB_writer(rs.getString("b_writer"));
				vo.setB_subject(rs.getString("b_subject"));
				vo.setB_content(rs.getString("b_content"));
				vo.setB_ref(rs.getInt("b_ref"));
				vo.setB_ref_step(rs.getInt("b_ref_step"));
				vo.setB_ref_level(rs.getInt("b_ref_level"));
				vo.setB_reg_date(rs.getTimestamp("b_reg_date"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return vo;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		int updateCnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE board SET b_subject=?,b_content=? WHERE b_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getB_subject());
			pstmt.setString(2, vo.getB_content());
			pstmt.setInt(3, vo.getB_id());
			updateCnt=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int insertCnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			conn=dataSource.getConnection();
			
			int num= vo.getB_id();
			int ref=vo.getB_ref();
			int ref_step=vo.getB_ref_step();
			int ref_level=vo.getB_ref_level();
			if(num==0) {
				sql="SELECT MAX(b_id) as maxNum FROM board";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				
				//첫글이 아닌경우: num과 ref는 동일
				if(rs.next()) {
					ref=rs.getInt("maxNum")+1;
				}else {//첫글인 경우 
					ref=1;
				}
				ref_step=0;
				ref_level=0;
			}else {//답변글인 경우
				//삽입할 글보다 아래쪽 글들을 한줄씩 밀러난다.
				sql="UPDATE board SET b_ref_step=b_ref_step+1 WHERE b_ref=? AND b_ref_step>? ";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,ref);
				pstmt.setInt(2,ref_step);
				pstmt.executeUpdate();
				ref_step++;
				ref_level++;
			}
			pstmt.close();
			
			//답변글이 아닌경우(제목글인 경우)
			sql="INSERT INTO board(b_id,mb_id,b_writer, b_subject, b_content,b_ref,b_ref_step,b_ref_level,b_reg_date) " + 
					" VALUES(BOARD_SEQ.nextval,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getMb_id());
			pstmt.setString(2,vo.getB_writer());
			pstmt.setString(3, vo.getB_subject());
			pstmt.setString(4, vo.getB_content());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, ref_step);
			pstmt.setInt(7, ref_level);
			pstmt.setTimestamp(8, vo.getB_reg_date());
			insertCnt=pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}

	@Override
	public int deleteBoard(int b_id) {
		int deleteCnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM board WHERE b_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				int ref= rs.getInt("b_ref");
				int ref_step= rs.getInt("b_ref_step");
				int ref_level= rs.getInt("b_ref_level");
				
				sql="SELECT * FROM board WHERE b_ref=? AND b_ref_step=?+1 AND b_ref_level>?";
				
				pstmt.close();
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);
				pstmt.setInt(3, ref_level);
				
				rs.close();
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					sql="DELETE board WHERE b_id=? OR(b_ref=? AND b_ref_level >?)";
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, b_id);
					pstmt.setInt(2, ref);
					pstmt.setInt(3, ref_level);
					deleteCnt=pstmt.executeUpdate();
				}else {
					sql="DELETE board WHERE b_id=?";
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, b_id);
					deleteCnt=pstmt.executeUpdate();
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}
	
}
