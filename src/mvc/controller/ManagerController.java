package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.AccountDAOImpl;
import mvc.service.AccountServiceImpl;
import mvc.service.BoardServiceImpl;
import mvc.service.MembersService;
import mvc.service.MembersServiceImpl;
import mvc.service.OrderServiceImpl;
import mvc.service.ProductService;
import mvc.service.ProductServiceImpl;
import mvc.service.CategoryServiceImpl;


/**
 * Servlet implementation class Controller
 */
@WebServlet("*.mc")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ManagerController() {
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
		if(url.equals("/settlement.mc")||url.equals("/*.mc")) {//메인
			AccountServiceImpl account=new AccountServiceImpl();
			account.accountView(req, res);
			viewPage="/manager/settlement.jsp";
		}else if(url.equals("/productManagement.mc")) {
			ProductServiceImpl product =new  ProductServiceImpl();
			product.ProductSellerList(req,  res);
			viewPage="/manager/productManagement.jsp";
		}else if(url.equals("/addproducts.mc")) {//상품관리 페이지
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			viewPage="/manager/addproducts.jsp";
		}else if(url.equals("/addproductAction.mc")) {//상품 추가 페이지
			ProductServiceImpl product =new  ProductServiceImpl();
			product.addProduct(req, res);
			viewPage="/manager/addproductAction.jsp";
		}else if(url.equals("/deleteproductAction.mc")) {//상품 추가 페이지
			ProductServiceImpl product =new  ProductServiceImpl();
			product.deleteproductAction(req, res);
			viewPage="/manager/deleteproductAction.jsp";
		}else if(url.equals("/updateproducts.mc")) {//상품 추가 페이지
			ProductServiceImpl product =new  ProductServiceImpl();
			product.productInfo(req, res);
			viewPage="/manager/updateproducts.jsp";
		}else if(url.equals("/orderApproval.mc")) {//판매자 구매승인 페이지
			OrderServiceImpl order=new OrderServiceImpl();
			order.orderView(req, res,3);
			viewPage="/manager/manager_sales.jsp";
		}else if(url.equals("/orderConfirmAction.mc")) {//판매자 구매승인 페이지
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/manager/orderConfirmAction.jsp";
		}else if(url.equals("/orderCancelAction.mc")) {//판매자 구매승인 페이지
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/manager/orderCancelAction.jsp";
		}else if(url.equals("/refundApproval.mc")) {//판매자 구매승인 페이지
			OrderServiceImpl order=new OrderServiceImpl();
			order.orderView(req, res,4);
			viewPage="/manager/manager_refund.jsp";
		}else if(url.equals("/managerRefundConfirmAction.mc")) {//판매자 구매승인 페이지
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/manager/managerRefundConfirmAction.jsp";
		}else if(url.equals("/updateproductAction.mc")) {//판매자 구매승인 페이지
			ProductServiceImpl product =new  ProductServiceImpl();
			product.updateproductAction(req, res);
			viewPage="/manager/updateproductAction.jsp";
		}else if(url.equals("/adminSettlement.mc")) {//판매자 구매승인 페이지
			AccountServiceImpl account=new AccountServiceImpl();
			account.adminAccountView(req, res);
			viewPage="/manager/settlement.jsp";
		}else if(url.equals("/productAdmin.mc")) {
			ProductServiceImpl product =new  ProductServiceImpl();
			product.productadminList(req,  res);
			viewPage="/manager/productManagement.jsp";
		}else if(url.equals("/manager_memberInfo.mc")) {
			MembersServiceImpl member = new MembersServiceImpl();
			member.memberList(req, res);
			viewPage="/manager/manager_memberInfo.jsp";
		}else if(url.equals("/deleteMemberAction.mc")) {
			MembersServiceImpl member = new MembersServiceImpl();
			member.adminDeleteMemberAction(req, res);
			viewPage="/manager/deleteMemberAction.jsp";
		}
		
		
		
		
		RequestDispatcher dispatcher=req.getRequestDispatcher(viewPage);
		dispatcher.forward(req,res);
	}

}
