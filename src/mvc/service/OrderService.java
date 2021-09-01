package mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {
	public void nowOrderView(HttpServletRequest req, HttpServletResponse res);
	public void cartOrderView(HttpServletRequest req, HttpServletResponse res);
	public void orderAction(HttpServletRequest req, HttpServletResponse res);
	public void updateOrder(HttpServletRequest req, HttpServletResponse res);
	public void orderView(HttpServletRequest req, HttpServletResponse res,int type);
}

