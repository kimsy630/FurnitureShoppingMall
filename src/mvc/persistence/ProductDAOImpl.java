package mvc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mvc.vo.CategorysVo;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;
import mvc.vo.SearchVO;

public class ProductDAOImpl implements ProductDAO{
	private static ProductDAOImpl instance=null;
	
	DataSource dataSource;
	
	public static ProductDAOImpl getInstance() {
		if(instance==null)instance=new ProductDAOImpl();
		return instance;
	}
	
	private ProductDAOImpl() {
		dataSource = DataSourceDAO.getInstance().getDataSource();
	}

	@Override
	public List<ProductsVO> newProduct(){
		List<ProductsVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM (SELECT ROWNUM rnum , p.* " + 
					"        FROM (select * " + 
					"                FROM products " + 
					"               ORDER BY p_id DESC) p) " + 
					"WHERE rnum<=8";
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<ProductsVO>();
				do {
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_category(rs.getString("p_category"));
					vo.setP_count(rs.getInt("p_count"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					vo.setP_image2(rs.getString("p_image2"));
					vo.setP_image3(rs.getString("p_image3"));
					vo.setP_saleCount(rs.getInt("p_saleCount"));
					vo.setP_readCount(rs.getInt("p_readCount"));
					vo.setP_date(rs.getTimestamp("p_date"));
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
				e.printStackTrace();
			}
		}
		return dto;
	}
	@Override
	public int insertProduct(ProductsVO vo) {
		int insertP=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="INSERT INTO products(p_id , mb_id ,p_name,p_category , p_count,p_price,p_saleprice,p_image,p_image2,p_image3,p_date) " + 
					 " VALUES (products_seq.nextval,?,?,?,?,?,?, ?,?,?,?)  " ;
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getMb_id());
			pstmt.setString(2,vo.getP_name());
			pstmt.setString(3,vo.getP_category());
			pstmt.setInt(4,vo.getP_count());
			pstmt.setInt(5,vo.getP_price());
			pstmt.setInt(6,vo.getP_saleprice());
			pstmt.setString(7, vo.getP_image());
			pstmt.setString(8, vo.getP_image2());
			pstmt.setString(9, vo.getP_image3());
			pstmt.setTimestamp(10, vo.getP_date());
			insertP=pstmt.executeUpdate();
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
		return insertP;
	}

	@Override
	public int updateProduct(ProductsVO vo) {
		int updateP=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE products "+
						  "SET p_name=?,p_category=? , p_count=?,p_price=?,p_saleprice=?,p_image=?,p_image2=?,p_image3=? "+
						" WHERE p_id = ?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getP_name());
			pstmt.setString(2,vo.getP_category());
			pstmt.setInt(3,vo.getP_count());
			pstmt.setInt(4,vo.getP_price());
			pstmt.setInt(5,vo.getP_saleprice());
			pstmt.setString(6, vo.getP_image());
			pstmt.setString(7, vo.getP_image2());
			pstmt.setString(8, vo.getP_image3());
			pstmt.setInt(9, vo.getP_id());
			updateP=pstmt.executeUpdate();
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
		return updateP;
	}

	@Override
	public List<ProductsVO> productSellerList(String mb_id, int type) {
		List<ProductsVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * " + 
					"  FROM products " + 
					" WHERE mb_id= ? " + 
					"ORDER BY CASE ? WHEN 1 then p_id   " + 
					"                WHEN 2 then p_saleCount " + 
					"                WHEN 3 then p_readCount  END DESC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mb_id);
			pstmt.setInt(2, type);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<ProductsVO>();
				do {
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_category(rs.getString("p_category"));
					vo.setP_count(rs.getInt("p_count"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					vo.setP_image2(rs.getString("p_image2"));
					vo.setP_image3(rs.getString("p_image3"));
					vo.setP_saleCount(rs.getInt("p_saleCount"));
					vo.setP_readCount(rs.getInt("p_readCount"));
					vo.setP_date(rs.getTimestamp("p_date"));
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Override
	public List<ProductsVO> productList(SearchVO searchVO) {
		List<ProductsVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * " + 
					"FROM (SELECT  ROWNUM rnum ,p.* " + 
					"  FROM (SELECT   * " + 
					"        FROM (SELECT *  " + 
					"                FROM products " ;
			if(searchVO.getCategory()==null) {
					sql+="          WHERE p_name LIKE ? ) ";
			}
			else{
					sql+=" 			WHERE p_category in(SELECT category_id " + 
						"            						FROM categorys " + 
						"			 						START WITH category_id = ? " + 
						"			 				CONNECT BY PRIOR category_id = category_parents)) " ;
			}
			sql+="            ORDER BY CASE WHEN  ?=1 AND p_count=0  then p_id END DESC, " + 
			"                     CASE WHEN  ?=1 AND p_count!=0 then p_id END DESC, " + 
			
			"                      CASE WHEN ?=2 AND p_count=0  then p_saleCount END DESC, " + 
			"                      CASE WHEN ?=2 AND p_count!=0 then p_saleCount END DESC, " + 
			
			"                      CASE WHEN ?=3 AND p_count=0  then p_readCount END DESC, " + 
			"                      CASE WHEN ?=3 AND p_count!=0 then p_readCount END DESC, " + 
			"                      CASE WHEN ?=4 AND p_count=0  then p_price END DESC, " + 
			"                      CASE WHEN ?=4 AND p_count!=0 then p_price END DESC, " + 
			"                      CASE WHEN ?=5 AND p_count!=0 then p_price END ASC, " + 
			"                      CASE WHEN ?=5 AND p_count=0  then p_price END ASC, " + 
			"                      CASE WHEN ?=6 AND p_count=0  then p_name END ASC, " + 
			"                      CASE WHEN ?=6 AND p_count!=0 then p_name END ASC) p) " + 
			"WHERE rnum>=? AND rnum<=? ";
			pstmt=conn.prepareStatement(sql);

			if(searchVO.getCategory()==null) {
				pstmt.setString(1, "%"+searchVO.getSearch()+"%");
			}else {
				pstmt.setString(1, searchVO.getCategory()); 
			}
			for(int i=2;i<=13;i++) {
				pstmt.setInt(i, searchVO.getType());
			}
			pstmt.setInt(14, searchVO.getStart());
			pstmt.setInt(15, searchVO.getEnd());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<ProductsVO>();
				do {
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_category(rs.getString("p_category"));
					vo.setP_count(rs.getInt("p_count"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					vo.setP_image2(rs.getString("p_image2"));
					vo.setP_image3(rs.getString("p_image3"));
					vo.setP_saleCount(rs.getInt("p_saleCount"));
					vo.setP_readCount(rs.getInt("p_readCount"));
					vo.setP_date(rs.getTimestamp("p_date"));
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
				e.printStackTrace();
			}
		}
		return dto;
	}
	@Override
	public List<ProductsVO> productCategoryList(int start, int end,String category, int type) {
		List<ProductsVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * " + 
					"FROM (SELECT  ROWNUM rnum ,p.* " + 
					"  FROM (SELECT   * " + 
					"        FROM (SELECT *  " + 
					"                FROM products " + 
					"               WHERE p_category in(SELECT category_id " + 
					"                                     FROM categorys " + 
					"                               START WITH category_id = ? " + 
					"                               CONNECT BY PRIOR category_id = category_parents)) " + 
					"            ORDER BY CASE WHEN  ?=1 AND p_count=0  then p_id END DESC, " + 
					"                     CASE WHEN  ?=1 AND p_count!=0 then p_id END DESC, " + 
					"                      CASE WHEN ?=2 AND p_count=0  then p_saleCount END DESC, " + 
					"                      CASE WHEN ?=2 AND p_count!=0 then p_saleCount END DESC, " + 
					"                      CASE WHEN ?=3 AND p_count=0  then p_readCount END DESC, " + 
					"                      CASE WHEN ?=3 AND p_count!=0 then p_readCount END DESC, " + 
					"                      CASE WHEN ?=4 AND p_count=0  then p_price END DESC, " + 
					"                      CASE WHEN ?=4 AND p_count!=0 then p_price END DESC, " + 
					"                      CASE WHEN ?=5 AND p_count!=0 then p_price END ASC, " + 
					"                      CASE WHEN ?=5 AND p_count=0  then p_price END ASC, " + 
					"                      CASE WHEN ?=6 AND p_count=0  then p_name END ASC, " + 
					"                      CASE WHEN ?=6 AND p_count!=0 then p_name END ASC) p) " + 
					"WHERE rnum>=? AND rnum<=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			for(int i=2;i<=13;i++) {
				pstmt.setInt(i, type);
			}
			pstmt.setInt(14, start);
			pstmt.setInt(15, end);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<ProductsVO>();
				do {
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_category(rs.getString("p_category"));
					vo.setP_count(rs.getInt("p_count"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					vo.setP_image2(rs.getString("p_image2"));
					vo.setP_image3(rs.getString("p_image3"));
					vo.setP_saleCount(rs.getInt("p_saleCount"));
					vo.setP_readCount(rs.getInt("p_readCount"));
					vo.setP_date(rs.getTimestamp("p_date"));
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	@Override
	public ProductsVO productDetail(int p_id) {
		ProductsVO vo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * " + 
					   "  FROM products " + 
					   " WHERE p_id= ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo =new ProductsVO();
				vo.setP_id(rs.getInt("p_id"));
				vo.setMb_id(rs.getString("mb_id"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_category(rs.getString("p_category"));
				vo.setP_count(rs.getInt("p_count"));
				vo.setP_price(rs.getInt("p_price"));
				vo.setP_saleprice(rs.getInt("p_saleprice"));
				vo.setP_score(rs.getInt("p_score"));
				vo.setP_image(rs.getString("p_image"));
				vo.setP_image2(rs.getString("p_image2"));
				vo.setP_image3(rs.getString("p_image3"));
				vo.setP_saleCount(rs.getInt("p_saleCount"));
				vo.setP_readCount(rs.getInt("p_readCount"));
				vo.setP_date(rs.getTimestamp("p_date"));
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
		return vo;
	}

	@Override
	public void productReadCount(int p_id) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE  products " + 
					   "  SET p_readCount=p_readCount+1 " + 
					   " WHERE p_id= ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			pstmt.executeUpdate();
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
	}

	@Override
	public int productCount(SearchVO searchVO) {
		int count=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT  COUNT(*) as cnt " + 
					"     FROM products "; 

			if(searchVO.getCategory()==null) {
				sql+=	"    WHERE p_name LIKE ?";
			}else {
				sql+=	"    WHERE p_category in( SELECT category_id " + 
						"                           FROM categorys " + 
						"                          START WITH category_id = ? " + 
						"                        CONNECT BY PRIOR category_id = category_parents)";
			}
			pstmt=conn.prepareStatement(sql);
			if(searchVO.getCategory()==null) pstmt.setString(1, "%"+searchVO.getSearch()+"%");
			else pstmt.setString(1, searchVO.getCategory());
			rs=pstmt.executeQuery();
			if(rs.next())  count = rs.getInt("cnt");
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
		return count;
	}
	@Override
	public int productCategoryCount(String category) {
		int count=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT  COUNT(*) as cnt " + 
					"     FROM products " + 
					"    WHERE p_category in( SELECT category_id " + 
					"                           FROM categorys " + 
					"                          START WITH category_id = ? " + 
					"                        CONNECT BY PRIOR category_id = category_parents)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs=pstmt.executeQuery();
			if(rs.next())  count = rs.getInt("cnt");
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
		return count;
	}

	@Override
	public ArrayList<CategorysVo> categoryCount(String category) {
		ArrayList<CategorysVo>  dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT CONNECT_BY_ISLEAF AS LEAF,category_parents " + 
						"FROM categorys " + 
						"START WITH category_id = ? " + 
						"CONNECT BY PRIOR category_id = category_parents ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs=pstmt.executeQuery();
			int leaf=1;
			String parents="";
			if(rs.next()) {
				leaf=rs.getInt("LEAF");
				parents=rs.getString("category_parents");
			}
			rs.close();
			pstmt.close();
			
			sql="SELECT c.category_id, COUNT(p.p_category) as cnt " + 
					"FROM products p RIGHT JOIN  ( SELECT category_id " + 
					"                                FROM categorys " + 
					"                               WHERE category_parents=?) c " + 
					"  ON p.p_category =c.category_id " + 
					"GROUP BY c.category_id ";
			pstmt=conn.prepareStatement(sql);
			
			if(leaf==0){//자식이있을경우 0
				pstmt.setString(1, category);
			}else {//없을경우 1
				pstmt.setString(1, parents);
			}
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<CategorysVo>();
				do {
					CategorysVo vo=new CategorysVo();
					vo.setCategory_id(rs.getString("category_id"));
					vo.setCount(rs.getInt("cnt"));
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
	public int deleteProducte(int p_id) {
		int deleteP=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="DELETE products "+
						" WHERE p_id = ? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			deleteP=pstmt.executeUpdate();
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
		return deleteP;
	}

	@Override
	public void productOrder(int p_id, int count) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=dataSource.getConnection();
			String sql="UPDATE products " + 
					"      SET p_count = p_count+? " + 
					"	 WHERE p_id=? AND 0<=p_count+? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, p_id);
			pstmt.setInt(3, count);
			pstmt.executeUpdate();
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
	}

	@Override
	public int productOrderCheck(List<OrderVO> vo) {
		int count =0;
		//0 = 사려는 상품수가 재고수보다 많다면
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			
			//구매하려는 상품이 재고가 사려는 수만큼 남아있는지 확인용
			String sql="SELECT COUNT(*) cnt " + 
					" FROM products " + 
					" WHERE ";
			for(int i=0;i<vo.size();i++) {
				sql+=" (p_id="+vo.get(i).getP_id()+" AND 0<=p_count-"+vo.get(i).getOrder_count()+" ) ";
				if(vo.size()-1 !=i)sql+=" OR ";
			}
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				
				if(rs.getInt("cnt")==vo.size()) {//구매하려는 list의 크기와 위에서 구한 count 값이 같으면 모두구매가능한 수량
					rs.close();
					
					//구매가능하기때문에 구매한 상품만큼 재고수량은 빼줌  p_saleCount는 판매수량, p_count는 재고수량
					for(int i=0;i<vo.size();i++) {
						pstmt.close();
						sql=" UPDATE products "+
						       "SET p_count=p_count-? ,p_saleCount =p_saleCount+? "+
							  " WHERE p_id = ?";
						pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1,vo.get(i).getOrder_count());
						pstmt.setInt(2,vo.get(i).getOrder_count());
						pstmt.setInt(3,vo.get(i).getP_id());
						pstmt.executeUpdate();
					}
					count=1;//0만아니면 아무거나 상관없음
				}
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
		return count;
	}

	@Override
	public List<ProductsVO> productAdminList(int type) {
		List<ProductsVO> dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * " + 
					"  FROM products " + 
					"ORDER BY CASE ? WHEN 1 then p_id   " + 
					"                WHEN 2 then p_saleCount " + 
					"                WHEN 3 then p_readCount  END DESC";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ArrayList<ProductsVO>();
				do {
					ProductsVO vo =new ProductsVO();
					vo.setP_id(rs.getInt("p_id"));
					vo.setMb_id(rs.getString("mb_id"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_category(rs.getString("p_category"));
					vo.setP_count(rs.getInt("p_count"));
					vo.setP_price(rs.getInt("p_price"));
					vo.setP_saleprice(rs.getInt("p_saleprice"));
					vo.setP_score(rs.getInt("p_score"));
					vo.setP_image(rs.getString("p_image"));
					vo.setP_image2(rs.getString("p_image2"));
					vo.setP_image3(rs.getString("p_image3"));
					vo.setP_saleCount(rs.getInt("p_saleCount"));
					vo.setP_readCount(rs.getInt("p_readCount"));
					vo.setP_date(rs.getTimestamp("p_date"));
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
				e.printStackTrace();
			}
		}
		return dto;
	}

	
}
