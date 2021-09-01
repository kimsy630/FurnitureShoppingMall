package mvc.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ProductService {
	public void addProduct(HttpServletRequest req, HttpServletResponse res) throws IOException;
	public void ProductSellerList(HttpServletRequest req, HttpServletResponse res);
	public void productCategoryList(HttpServletRequest req, HttpServletResponse res);
	public void deleteproductAction(HttpServletRequest req, HttpServletResponse res);
	public void productInfo(HttpServletRequest req, HttpServletResponse res);
	public void updateproductAction(HttpServletRequest req, HttpServletResponse res) throws IOException ;
	public void indexView(HttpServletRequest req, HttpServletResponse res);
	public void productadminList(HttpServletRequest req, HttpServletResponse res);

}
