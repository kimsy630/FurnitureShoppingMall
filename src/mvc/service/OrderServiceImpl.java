package mvc.service;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.CartDAOImpl;
import mvc.persistence.MembersDAO;
import mvc.persistence.MembersDAOImpl;
import mvc.persistence.OrderDAOImpl;
import mvc.persistence.ProductDAOImpl;
import mvc.vo.MembersVO;
import mvc.vo.OrderFormVO;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;

public class OrderServiceImpl implements OrderService{
	@Override
	public void nowOrderView(HttpServletRequest req, HttpServletResponse res) {
		int count = Integer.parseInt(req.getParameter("count"));
		int p_id= Integer.parseInt(req.getParameter("p_id"));
		int p_price=Integer.parseInt(req.getParameter("price"));
		String p_name=req.getParameter("p_name");
		String p_image=req.getParameter("p_image");
		MembersVO member=MembersDAOImpl.getInstance().getMemberInfo((String)req.getSession().getAttribute("mb_id"));
		
		List<OrderFormVO> dto =new ArrayList<OrderFormVO>();
		OrderFormVO vo=new OrderFormVO();
		vo.setId(0);
		vo.setP_id(p_id);
		//vo.setCart_id();
		vo.setP_image(p_image);
		vo.setP_name(p_name);
		vo.setCount(count);
		vo.setPrice(p_price);
		
		
		p_price-=member.getRankCal(p_price);
		vo.setSaleprice(p_price);
		
		vo.setTotalprice(p_price*vo.getCount());
		
		vo.setPoint(member.getRankCal(vo.getTotalprice()));
		dto.add(vo);
		
		req.setAttribute("member",member);
		req.setAttribute("dto", dto);
		req.setAttribute("state",0);
	}

	@Override
	public void cartOrderView(HttpServletRequest req, HttpServletResponse res) {
		MembersVO member=MembersDAOImpl.getInstance().getMemberInfo((String)req.getSession().getAttribute("mb_id"));
		List<OrderFormVO> dto =new ArrayList<OrderFormVO>();
		String[] cart_id=req.getParameterValues("chioce");
		System.out.println(cart_id);
		
		for(int i=0;i<cart_id.length;i++) {
			int count = Integer.parseInt(req.getParameter(cart_id[i]+"count"));

			System.out.println("count : " + count);
			int p_id= Integer.parseInt(req.getParameter(cart_id[i]+"p_id"));
			int p_price=Integer.parseInt(req.getParameter(cart_id[i]+"price"));
			String p_name=req.getParameter(cart_id[i]+"p_name");
			String p_image=req.getParameter(cart_id[i]+"p_image");
			OrderFormVO vo=new OrderFormVO();
			vo.setId(i);
			vo.setP_id(p_id);
			vo.setCart_id(Integer.parseInt(cart_id[i]));
			vo.setP_image(p_image);
			vo.setP_name(p_name);
			vo.setCount(count);
			vo.setPrice(p_price);
			p_price-=member.getRankCal(p_price);
			vo.setSaleprice(p_price);
			vo.setTotalprice(p_price*vo.getCount());
			vo.setPoint(member.getRankCal(p_price));
			dto.add(vo);
		}
		
		req.setAttribute("member",member);
		req.setAttribute("dto", dto);
		req.setAttribute("state",1);
	}

	@Override
	public void orderAction(HttpServletRequest req, HttpServletResponse res) {
		int salepoint =  Integer.parseInt(req.getParameter("point"));//사용한 포인트
		
		String[] orderArr=req.getParameterValues("id");
		String order_name=req.getParameter("order_name");
		String order_ph=req.getParameter("order_ph");
		String order_address=req.getParameter("order_address");
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int state = Integer.parseInt(req.getParameter("state"));//장바구니 구매인지 바로 구매인지 확인용
		List<Integer> cartIdList=null;
		
		if(state==1) cartIdList=new ArrayList<Integer>(); 
		
		List<OrderVO> list =new ArrayList<OrderVO>();
		for(int i=0;i<orderArr.length;i++) { //구매한 상품수만큼 돌림
			int p_id =Integer.parseInt(req.getParameter(orderArr[i]+"p_id"));
			int count=Integer.parseInt(req.getParameter(orderArr[i]+"count"));
			int price=Integer.parseInt(req.getParameter(orderArr[i]+"price"));
			int point=Integer.parseInt(req.getParameter(orderArr[i]+"point"));
			
			if(state==1) {//장바구니 상품이라면 
				int cartId=Integer.parseInt(req.getParameter(orderArr[i]+"cart_id"));
				cartIdList.add(cartId);//구매 완료라면 지워주기위해 담아둠
			}
			
			OrderVO vo=new OrderVO();
			vo.setP_id(p_id);
			System.out.println("orderArr"+orderArr[i]+" : " + count);
			vo.setOrder_count(count);
			vo.setMb_id(mb_id);
			vo.setOrder_price(price);
			vo.setOrder_point(point);
			vo.setOrder_name(order_name);
			vo.setOrder_ph(order_ph);
			vo.setOrder_address(order_address);
			vo.setOrder_date(new Timestamp(System.currentTimeMillis()));
			vo.setOrder_state(0);//state 상태로 구매, 환불등 상태 보여줌
			list.add(vo);
		}
		//구매하려는 상품의 재고수량이 전부있는지확인하고 재고수량 뺴주기용
		int updateP=ProductDAOImpl.getInstance().productOrderCheck(list);
		if(updateP!=0) {//0이아니면 구매상품수량만큼 지워준거 
			if(salepoint>0)//사용한 포인트가있다면 지워줌
				MembersDAOImpl.getInstance().memberPointUpdate(mb_id, -salepoint);
			if(state==1) {//선택한 장바구니 리스트 지워줌
				CartDAOImpl.getInstance().OderDeleteCart(cartIdList);
			}
			OrderDAOImpl.getInstance().insertOrder(list);
		}
		req.setAttribute("updateP",updateP);
	}

	@Override
	public void updateOrder(HttpServletRequest req, HttpServletResponse res) {
		int order_state = Integer.parseInt(req.getParameter("state"));
		/*order_state
			0 : 손님이 구매신청		(손님 : 주문취소 | 관리자 : 주문 승인)
			1 : 관리자가 구매신청 승인 	(손님 : 환불, 구매확정 | 관리자 : 구매승인완료 글자만)
			2 : 
			3 : 
			4 : 
			5 : 
			6 : 
		 */
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		
		OrderDAOImpl dao=OrderDAOImpl.getInstance();
		if(order_state==2) {
			OrderVO vo=dao.orderInfo(order_id);
			MembersDAOImpl.getInstance().memberBuyPointUpdate((String)req.getSession().getAttribute("mb_id"), vo.getOrder_point());
		}

		
		int updateO = dao.updateOrderState(order_id, order_state);
		req.setAttribute("updateO", updateO);
	}

	@Override
	public void orderView(HttpServletRequest req, HttpServletResponse res,int type) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		List<OrderVO> dto = OrderDAOImpl.getInstance().orderView(mb_id, type);
		System.out.println("asdas");
		req.setAttribute("dto", dto);
	}
	

}
