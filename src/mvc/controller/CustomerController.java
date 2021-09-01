package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.service.BoardServiceImpl;
import mvc.service.CartServiceImpl;
import mvc.service.MembersService;
import mvc.service.MembersServiceImpl;
import mvc.service.OrderService;
import mvc.service.OrderServiceImpl;
import mvc.service.ProductServiceImpl;
import mvc.service.CategoryServiceImpl;


/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CustomerController() {
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
		
		if(url.equals("/index.do")||url.equals("/*.do")) {//메인
			CategoryServiceImpl view=new CategoryServiceImpl();
			//메인 매뉴확인
			view.mainMenu(req, res);
			ProductServiceImpl product =new ProductServiceImpl();
			product.indexView(req, res);
			viewPage="/index/index.jsp";
		}else if(url.equals("/login.do")) {//로그인창
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			 viewPage="/common/login.jsp";
		}else if(url.equals("/loginAction.do")) {//로그인 창누르면
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			MembersServiceImpl service=new MembersServiceImpl();
			service.loginAction(req, res);//로그인 회원정보있는지 확인
			 viewPage="/common/login.jsp";
		}else if(url.equals("/join.do")) {//회원가입창
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			 viewPage="/customer/join.jsp";
		}else if(url.equals("/joinAction.do")) {//회원가입창
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			 MembersServiceImpl service=new MembersServiceImpl();
			 service.signInAction(req, res);//회원정보등록
			 viewPage="/customer/joinAction.jsp";
		}else if(url.equals("/corfirmId.do")) {//중복확인
			MembersServiceImpl service=new MembersServiceImpl();
			service.confirmId(req, res);//중복확인
			viewPage="/common/corfirmId.jsp";
		}else if(url.equals("/logout.do")) {//로그아웃
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);

			ProductServiceImpl product =new ProductServiceImpl();
			product.indexView(req, res);
			req.getSession().invalidate();//세션 지움
			viewPage="/index/index.jsp";
		}else if(url.equals("/mypage.do")) {//마이페이지
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			MembersServiceImpl service=new MembersServiceImpl();
			service.memberInfo(req, res);//회원정보가지고옴
			viewPage="/customer/mypage.jsp";
		}else if(url.equals("/memberUpdate.do")) {//회원정보수정정보 가지고오기
			MembersServiceImpl service=new MembersServiceImpl();
			service.memberInfo(req, res);
			viewPage="/customer/memberUpdate.jsp";
		}else if(url.equals("/updateAction.do")) {//회원정보 수정 버튼 누를때
			MembersServiceImpl service=new MembersServiceImpl();
			service.updateMemberAction(req, res);
			viewPage="/customer/memberUpdateAction.jsp";
		}else if(url.equals("/qna.do")) {//qna
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.boardList(req, res);
			viewPage="/common/qna.jsp";
		}else if(url.equals("/addqna.do")) {//qna 등록페이지
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.addQna(req, res);
			viewPage="/common/addqna.jsp";
		}else if(url.equals("/addqnaAction.do")) {//qna삽입
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.addQnaAction(req, res);
			board.boardList(req, res);
			viewPage="/common/qna.jsp";
		}else if(url.equals("/deleteqna.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.deleteQnaAction(req, res);
			board.boardList(req, res);
			viewPage="/common/qna.jsp";
		}else if(url.equals("/updateqna.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.updateView(req, res);
			viewPage="/common/addqna.jsp";
		}else if(url.equals("/updateqnaAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			BoardServiceImpl board=new BoardServiceImpl();
			board.updateAction(req, res);
			board.boardList(req, res);
			viewPage="/common/qna.jsp";
		}else if(url.equals("/addcart.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			CartServiceImpl cart=new CartServiceImpl();
			cart.addCart(req, res);
			viewPage="/customer/addcartAction.jsp";
		}else if(url.equals("/cart.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			CartServiceImpl cart=new CartServiceImpl();
			cart.cartList(req, res);
			viewPage="/customer/cart.jsp";
		}else if(url.equals("/deleteCartAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			CartServiceImpl cart=new CartServiceImpl();
			cart.delCart(req, res);
			viewPage="/customer/deleteCartAction.jsp";
		}else if(url.equals("/noworderform.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.nowOrderView(req, res);
			viewPage="/customer/orderform.jsp";
		}else if(url.equals("/orderAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			//order.nowBuyAction(req, res);
			order.orderAction(req, res);
			viewPage="/customer/orderAction.jsp";
		}else if(url.equals("/cartOrderView.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.cartOrderView(req, res);
			viewPage="/customer/orderform.jsp";
		}else if(url.equals("/buy.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.orderView(req, res,1);
			viewPage="/customer/buy.jsp";
		}else if(url.equals("/orderCancelAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/customer/orderCancelAction.jsp";
		}else if(url.equals("/customer_refund.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.orderView(req, res,2);
			viewPage="/customer/customer_refund.jsp";
		}else if(url.equals("/refundAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/customer/refundAction.jsp";
		}else if(url.equals("/memberRefundCancelAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/customer/memberRefundCancelAction.jsp";
		}else if(url.equals("/withdrawalPwdCheck.do")) {//qna삭제
			MembersServiceImpl member= new MembersServiceImpl();
			member.memberPwdCheck(req, res);
			viewPage="/customer/withdrawal.jsp";
		}else if(url.equals("/withdrawal.do")) {//qna삭제
			req.setAttribute("selectM", 0);
			viewPage="/customer/withdrawal.jsp";
		}else if(url.equals("/withdrawalAction.do")) {//qna삭제
			MembersServiceImpl member= new MembersServiceImpl();
			member.deleteMemberAction(req, res);
			req.getSession().invalidate();//세션 지움
			viewPage="/customer/withdrawalAction.jsp";
		}else if(url.equals("/orderConfirmAction.do")) {//qna삭제
			CategoryServiceImpl view=new CategoryServiceImpl();
			view.mainMenu(req, res);
			OrderServiceImpl order=new OrderServiceImpl();
			order.updateOrder(req, res);
			viewPage="/customer/orderConfirmAction.jsp";
		}
		
		RequestDispatcher dispatcher=req.getRequestDispatcher(viewPage);
		dispatcher.forward(req,res);
	}

}
