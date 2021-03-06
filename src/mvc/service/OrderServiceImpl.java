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
		int salepoint =  Integer.parseInt(req.getParameter("point"));//????????? ?????????
		
		String[] orderArr=req.getParameterValues("id");
		String order_name=req.getParameter("order_name");
		String order_ph=req.getParameter("order_ph");
		String order_address=req.getParameter("order_address");
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int state = Integer.parseInt(req.getParameter("state"));//???????????? ???????????? ?????? ???????????? ?????????
		List<Integer> cartIdList=null;
		
		if(state==1) cartIdList=new ArrayList<Integer>(); 
		
		List<OrderVO> list =new ArrayList<OrderVO>();
		for(int i=0;i<orderArr.length;i++) { //????????? ??????????????? ??????
			int p_id =Integer.parseInt(req.getParameter(orderArr[i]+"p_id"));
			int count=Integer.parseInt(req.getParameter(orderArr[i]+"count"));
			int price=Integer.parseInt(req.getParameter(orderArr[i]+"price"));
			int point=Integer.parseInt(req.getParameter(orderArr[i]+"point"));
			
			if(state==1) {//???????????? ??????????????? 
				int cartId=Integer.parseInt(req.getParameter(orderArr[i]+"cart_id"));
				cartIdList.add(cartId);//?????? ???????????? ?????????????????? ?????????
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
			vo.setOrder_state(0);//state ????????? ??????, ????????? ?????? ?????????
			list.add(vo);
		}
		//??????????????? ????????? ??????????????? ??????????????????????????? ???????????? ????????????
		int updateP=ProductDAOImpl.getInstance().productOrderCheck(list);
		if(updateP!=0) {//0???????????? ???????????????????????? ???????????? 
			if(salepoint>0)//????????? ????????????????????? ?????????
				MembersDAOImpl.getInstance().memberPointUpdate(mb_id, -salepoint);
			if(state==1) {//????????? ???????????? ????????? ?????????
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
			0 : ????????? ????????????		(?????? : ???????????? | ????????? : ?????? ??????)
			1 : ???????????? ???????????? ?????? 	(?????? : ??????, ???????????? | ????????? : ?????????????????? ?????????)
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
