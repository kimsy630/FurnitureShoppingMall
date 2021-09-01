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
import javax.sql.DataSource;

import mvc.vo.CartVO;
import mvc.vo.OrderFormVO;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;

public class CartDAOImpl implements CartDAO{
	private static CartDAOImpl instance=null;
	
	DataSource dataSource;
	private CartDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	public static CartDAOImpl getInstance() {
		if(instance==null)instance=new CartDAOImpl();
		return instance;
	}
	@Override
	public int insertCart(CartVO vo) {
		int insertC=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql=" INSERT INTO carts(cart_id,p_id,mb_id,cart_count) " + 
					   " VALUES(carts_seq.nextval,?,?,?)" ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,vo.getP_id());
			pstmt.setString(2,vo.getMb_id());
			pstmt.setInt(3,vo.getCart_count());
			insertC=pstmt.executeUpdate();
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
		return insertC;
	}
	@Override
	public int deleteCart(int num) {
		int deleteC=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql=" DELETE carts " + 
						"WHERE cart_id=?" ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			deleteC=pstmt.executeUpdate();
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
		return deleteC;
	}
	@Override
	public List<CartVO> memberCartList(String mb_id) {
		List<CartVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT p.* , c.cart_count, c.cart_id " + 
					"  FROM carts c " + 
					"  JOIN products p " + 
					"    ON c.p_id=p.p_id " + 
					" WHERE c.mb_id=? " ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<CartVO>();
				do {
					
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					CartVO cvo=new CartVO();
					cvo.setCart_id(rs.getInt("cart_id"));
					cvo.setCart_count(rs.getInt("cart_count"));
					cvo.setProduct(vo);
					dto.add(cvo);
				}while(rs.next());
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	@Override
	public void OderDeleteCart(List<Integer> vo) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql=" DELETE carts " + 
						"WHERE cart_id in(" ;
			for(int i=0;i<vo.size();i++) {
				sql+=vo.get(i);
				if(vo.size()-1!=i) {
					sql+=",";
				}
			}
			sql+=")";
			pstmt=conn.prepareStatement(sql);
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
	public int selectCart(String mb_id, int p_id) {
		int selectCnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql=" select cart_count " + 
					"from carts " + 
					"where mb_id = ? and p_id = ? " ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setInt(2,p_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectCnt = rs.getInt("cart_count");
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return selectCnt;
	}
	@Override
	public int updateCart(String mb_id, int p_id , int count) {
		int updateC=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE carts " + 
						"SET cart_count = ?" + 
						"WHERE  mb_id = ? and p_id = ? " ;
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, mb_id);
			pstmt.setInt(3, p_id);
			updateC = pstmt.executeUpdate();
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
		return updateC;
	}
	
}
