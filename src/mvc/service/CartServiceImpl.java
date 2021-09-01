package mvc.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.CartDAOImpl;
import mvc.persistence.MembersDAOImpl;
import mvc.persistence.ProductDAOImpl;
import mvc.vo.CartVO;
import mvc.vo.MembersVO;
import mvc.vo.ProductsVO;

public class CartServiceImpl implements CartService{

	@Override
	public void addCart(HttpServletRequest req, HttpServletResponse res) {
		CartDAOImpl dao = CartDAOImpl.getInstance();
		
		int count =Integer.parseInt(req.getParameter("count"));//화면에서 받아온
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		int p_id = Integer.parseInt(req.getParameter("p_id"));
		int p_count=Integer.parseInt(req.getParameter("p_count"));
		
					   //이미 담은상품인지 확인 있다면 수량 return 해줌
		int selectCnt = dao.selectCart(mb_id, p_id);
		int insertC = 0;
		
		if(selectCnt==0) {//카트에없는상품이라면
			CartVO vo =new CartVO();
			vo.setCart_count(count);
			vo.setMb_id(mb_id);
			vo.setP_id(p_id);
			insertC=dao.insertCart(vo);
		}
		else {//이미있는상품이라면
			
			//추가를하면 판매상품의 수량보다 많은지 확인
			if(p_count <selectCnt+count) {
				insertC=3;
				count=p_count;
			}else {//그냥 추가만 하면됨
				count+=selectCnt;
				insertC=2;
			}
			dao.updateCart(mb_id, p_id, count);
		}
		
		req.setAttribute("insertC", insertC);
	}

	@Override
	public void delCart(HttpServletRequest req, HttpServletResponse res) {
		String[] deleteList =req.getParameterValues("chioce");
		int deleteC =0;
		
		CartDAOImpl dao= CartDAOImpl.getInstance();
		
		for(int i=0;i<deleteList.length;i++) {
			deleteC = dao.deleteCart(Integer.parseInt(deleteList[i]));
		}
		req.setAttribute("deleteC",deleteC);
	}

	@Override
	public void cartList(HttpServletRequest req, HttpServletResponse res) {
		CartDAOImpl dao = CartDAOImpl.getInstance();
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		List<CartVO> dto = dao.memberCartList(mb_id);
		req.setAttribute("dto", dto);
		
		MembersVO member= MembersDAOImpl.getInstance().getMemberInfo(mb_id);
		req.setAttribute("member", member);
	}
	

}
