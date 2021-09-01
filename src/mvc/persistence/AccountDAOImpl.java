package mvc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import mvc.vo.AccountVO;
import mvc.vo.BoardVO;

public class AccountDAOImpl implements AccountDAO{
	
	private static AccountDAOImpl instance=null;
	
	DataSource dataSource;
	private AccountDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	
	public static AccountDAOImpl getInstance() {
		if(instance==null)instance=new AccountDAOImpl();
		return instance;
	}

	@Override
	public List<AccountVO> accountMonth(String mb_id) {
		List<AccountVO> dtos =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String sql="SELECT TO_CHAR(b.dt, 'YYYY-MM') AS hiredate " + 
					"    , NVL (TRUNC(SUM(a.sumorder)*0.92), 0) sumorder " + 
					"     FROM ( SELECT TO_CHAR(o.order_date, 'YYYY-MM-DD') AS hiredate , SUM(o.order_price) sumorder " + 
					"              FROM orders o JOIN products p  ON o.p_id = p.p_id " + 
					"             WHERE p.mb_id= ? AND o.order_state IN (2,3) AND o.order_date BETWEEN SYSDATE -365  AND SYSDATE " + 
					"             GROUP BY TO_CHAR(order_date, 'YYYY-MM-DD'))  a " + 
					"RIGHT JOIN (SELECT  TRUNC(add_months(sysdate,-11),'mm') -1+ LEVEL AS dt " + 
					"              FROM dual  " + 
					"           CONNECT BY LEVEL <= sysdate - TRUNC(add_months(sysdate,-11),'mm') +1)   b " + 
					"  ON b.dt = a.hiredate " + 
					"  GROUP BY ROLLUP(TO_CHAR(b.dt, 'YYYY-MM')) " + 
					"  ORDER BY TO_CHAR(b.dt, 'YYYY-MM') ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dtos=new ArrayList<AccountVO>();
				do {
					AccountVO vo=new AccountVO();
					vo.setDate(rs.getString("hiredate"));
					vo.setSum(rs.getInt("sumorder"));
					dtos.add(vo);
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
	public List<Integer> approvedCount(String mb_id) {
		List<Integer> dtos=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String sql="SELECT a.order_state,NVL(a.cnt,0) cnt" + 
					"                FROM (SELECT o.order_state,COUNT(o.order_state) cnt " + 
					"					  FROM orders o JOIN products p    " + 
					"					    ON o.p_id = p.p_id " + 
					"					 WHERE p.mb_id = ? " + 
					"					    AND (o.order_state =0 OR  o.order_state =6)   " + 
					"					GROUP BY o.order_state) a, " + 
					"                    (select LEVEL-1 lv " + 
					"                    FROM dual  " + 
					"                    where (level-1)=6 OR (level-1)=0  " + 
					"                    connect by level <= 7) b  " + 
					"               WHERE b.lv = a.order_state(+)  " + 
					"              ORDER BY b.lv ASC ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dtos=new ArrayList<Integer>();
				do {
					dtos.add(rs.getInt("cnt"));
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
	public List<AccountVO> adminAccountMonth() {
		List<AccountVO> dtos =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=dataSource.getConnection();
			String sql="SELECT TO_CHAR(b.dt, 'YYYY-MM') AS hiredate " + 
					"     , NVL(TRUNC(SUM(a.sumorder)*0.08), 0) sumorder " + 
					"  FROM ( SELECT TO_CHAR(order_date, 'YYYY-MM-DD') AS hiredate , SUM(order_price) sumorder " + 
					"           FROM orders " + 
					"          WHERE order_state IN (2,3) AND order_date BETWEEN SYSDATE -365  AND SYSDATE " + 
					"          GROUP BY TO_CHAR(order_date, 'YYYY-MM-DD'))  a " + 
					"RIGHT JOIN ( SELECT  TRUNC(add_months(sysdate,-11),'mm') -1+ LEVEL AS dt " + 
					"            FROM dual  " + 
					"         CONNECT BY LEVEL <= sysdate - TRUNC(add_months(sysdate,-11),'mm') +1) b " + 
					"  ON b.dt = a.hiredate " + 
					"  GROUP BY ROLLUP(TO_CHAR(b.dt, 'YYYY-MM')) " + 
					"  ORDER BY TO_CHAR(b.dt, 'YYYY-MM')";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dtos=new ArrayList<AccountVO>();
				do {
					AccountVO vo=new AccountVO();
					vo.setDate(rs.getString("hiredate"));
					vo.setSum(rs.getInt("sumorder"));
					dtos.add(vo);
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
}
