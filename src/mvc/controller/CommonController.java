package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.ProductServiceImpl;
import mvc.service.CategoryServiceImpl;

/**
 * Servlet implementation class CommonController
 */
@WebServlet("*.cc")
public class CommonController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
    public CommonController() {
        super();
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		action(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void action(HttpServletRequest req,HttpServletResponse res)
			throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		
		String viewPage="";
		
		String uri=req.getRequestURI();
		String contextPath=req.getContextPath();
		String url= uri.substring(contextPath.length());
		if(url.equals("/category.cc")) {//메인
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			ProductServiceImpl product=new ProductServiceImpl();
			product.productCategoryList(req, res);
			viewPage="/common/category.jsp";
		}else if(url.equals("/products.cc")) {//메인
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			ProductServiceImpl product=new ProductServiceImpl();
			product.productInfo(req, res);
			viewPage="/common/products.jsp";
		}
		
		
		RequestDispatcher dispatcher=req.getRequestDispatcher(viewPage);
		dispatcher.forward(req,res);
	}

}
