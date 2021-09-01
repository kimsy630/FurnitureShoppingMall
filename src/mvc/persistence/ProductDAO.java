package mvc.persistence;

import java.util.ArrayList;
import java.util.List;

import mvc.vo.CategorysVo;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;
import mvc.vo.SearchVO;

public interface ProductDAO {
	
	public List<ProductsVO> newProduct();
	
	public int insertProduct(ProductsVO vo);
	
	public int updateProduct(ProductsVO vo);
	
	public List<ProductsVO> productSellerList(String mb_id,int type);
	
	public List<ProductsVO> productCategoryList(int start, int end ,String category,int type);
	
	public List<ProductsVO> productList(SearchVO searchVO);
	
	public ProductsVO productDetail(int p_id);
	
	public void  productReadCount(int p_id);
	
	public int productCategoryCount(String category);
	
	public int productCount(SearchVO searchVO);
	
	public ArrayList<CategorysVo> categoryCount(String category);
	
	public int deleteProducte(int p_id);
	
	public void productOrder(int p_id,int count);
	
	public int productOrderCheck(List<OrderVO> vo);
	
	public List<ProductsVO> productAdminList(int type);
	
	
}
