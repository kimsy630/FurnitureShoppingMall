package mvc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import mvc.vo.MembersVO;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;

public class OrderDAOImpl implements OrderDAO{
	private static OrderDAOImpl instance=null;
	
	DataSource dataSource;
	private OrderDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	
	public static OrderDAOImpl getInstance() {
		if(instance==null)instance=new OrderDAOImpl();
		return instance;
	}

	@Override
	public int insertOrder(List<OrderVO> vo) {
		int insertO=1;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			for(int i=0;i<vo.size();i++) {
				String sql="INSERT INTO orders(order_id, p_id,mb_id,order_count,order_price,order_point,  order_name ,order_ph,order_address ,order_date, order_state) " + 
						" VALUES(orders_seq.nextval,?,?,?,?,?,?,?,?,?,?) " ;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, vo.get(i).getP_id());
				pstmt.setString(2,vo.get(i).getMb_id());
				pstmt.setInt(3,vo.get(i).getOrder_count());
				pstmt.setInt(4,vo.get(i).getOrder_price());
				pstmt.setInt(5,vo.get(i).getOrder_point());
				pstmt.setString(6,vo.get(i).getOrder_name());
				pstmt.setString(7,vo.get(i).getOrder_ph());
				pstmt.setString(8, vo.get(i).getOrder_address());
				pstmt.setTimestamp(9, vo.get(i).getOrder_date());
				pstmt.setInt(10, vo.get(i).getOrder_state());
				pstmt.executeUpdate();
				pstmt.close();
			}

		}catch (SQLException e) {
			insertO = 0 ;
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertO;
	}

	@Override
	public int updateOrderState(int order_id,int order_state) {
		int updateO=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE orders " + 
					" SET order_state=? "+
					" WHERE order_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,order_state);
			pstmt.setInt(2,order_id);
			updateO=pstmt.executeUpdate();

		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateO;
	}

	@Override
	public List<OrderVO> orderView(String mb_id,int type) {
		List<OrderVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT o.*,p.p_image, p.p_name " + 
					"FROM  orders o,products p " + 
					"WHERE  p.p_id = o.p_id  " ;
			if(type==1) {//회원 구매목록
				sql+="  AND o.mb_id = ? " + 
						"  AND o.order_state<=5 " + 
						"ORDER BY o.order_id DESC";
			}else if(type==2) {//회원 환불목록
				sql+=" AND o.mb_id = ? " + 
						"  AND o.order_state>5 " + 
						"ORDER BY o.order_id DESC";
			}else if(type==3) {//판매자 구매승인목록
				sql+="  AND p.mb_id =? " + 
					"  AND o.order_state<=5 " + 
					"ORDER BY o.order_state,o.order_id ";
			}else if(type==4) {//회원 환불목록
				sql+="  AND p.mb_id =? " + 
						"  AND o.order_state>5 " + 
						"ORDER BY o.order_state,o.order_id ";
			}
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,mb_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<OrderVO>();
				do {
					OrderVO vo=new OrderVO();
					vo.setOrder_id(rs.getInt("order_id"));
					vo.setP_id(rs.getInt("p_id"));
					vo.setOrder_count(rs.getInt("order_count"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setOrder_price(rs.getInt("order_price"));
					vo.setOrder_point(rs.getInt("order_point"));
					vo.setOrder_name(rs.getString("order_name"));
					vo.setOrder_ph(rs.getString("order_ph"));
					vo.setOrder_address(rs.getString("order_address"));
					vo.setOrder_date(rs.getTimestamp("order_date"));
					vo.setOrder_state(rs.getInt("order_state"));
					ProductsVO product =new ProductsVO();
					product.setP_image(rs.getString("p_image"));
					product.setP_name(rs.getString("p_name"));
					vo.setProduct(product);
					dto.add(vo);
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
	public OrderVO orderInfo(int order_id) {
		OrderVO vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM orders WHERE order_id = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,order_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new OrderVO();
				vo.setOrder_id(rs.getInt("order_id"));
				vo.setP_id(rs.getInt("p_id"));
				vo.setOrder_count(rs.getInt("order_count"));
				vo.setMb_id(rs.getString("mb_id"));
				vo.setOrder_price(rs.getInt("order_price"));
				vo.setOrder_point(rs.getInt("order_point"));
				vo.setOrder_name(rs.getString("order_name"));
				vo.setOrder_ph(rs.getString("order_ph"));
				vo.setOrder_address(rs.getString("order_address"));
				vo.setOrder_date(rs.getTimestamp("order_date"));
				vo.setOrder_state(rs.getInt("order_state"));
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
		return vo;
	}
}
