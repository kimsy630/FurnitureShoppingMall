package mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CartService {
	public void addCart(HttpServletRequest req, HttpServletResponse res);
	public void delCart(HttpServletRequest req, HttpServletResponse res);
	public void cartList(HttpServletRequest req, HttpServletResponse res);
}
