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

import mvc.vo.CategorysVo;

public class CategoryDAOImpl implements CategoryDAO{
	private static CategoryDAOImpl instance=null;
	
	DataSource dataSource;
	private CategoryDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}
	
	public static CategoryDAOImpl getInstance() {
		if(instance==null)instance=new CategoryDAOImpl();
		return instance;
	}
	
	@Override
	public ArrayList<CategorysVo> categoryView(String search) {
		ArrayList<CategorysVo>  dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String  sql="SELECT LEVEL, category_id, category_parents " + 
					 " FROM categorys " + 
					 " START WITH category_parents is null " + 
					 " CONNECT BY PRIOR category_id=category_parents";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<CategorysVo>();
				do {
					CategorysVo vo=new CategorysVo();
					vo.setCategory_id(rs.getString("category_id"));
					vo.setCategory_parents(rs.getString("category_parents"));
					//dto.add(vo);
					if(vo.getCategory_parents()==null) {
						dto.add(vo);
					}else {
						if(dto.get(dto.size()-1).getChild()==null)
							dto.get(dto.size()-1).setChild(new ArrayList<CategorysVo>());
						dto.get(dto.size()-1).getChild().add(vo);
					}
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
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	@Override
	public ArrayList<CategorysVo> categoryCount(String search) {
		ArrayList<CategorysVo>  dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="";
			sql="SELECT p_category,COUNT(*) as cnt " + 
				"  FROM products " + 
				" WHERE p_category in( SELECT category_id " + 
				"                        FROM categorys " + 
				"                        START WITH category_id = ? " +  
				"                        CONNECT BY PRIOR category_id = category_parents) " + 
				" GROUP BY p_category";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, search);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<CategorysVo>();
				do {
					CategorysVo vo=new CategorysVo();
					vo.setCategory_id(rs.getString("category_id"));
					vo.setCategory_parents(rs.getString("category_parents"));
					vo.setCount(rs.getInt("count"));
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
}
