package mvc.persistence;

import java.util.List;


import mvc.vo.OrderVO;

public interface OrderDAO {
	public int insertOrder(List<OrderVO> vo);
	public int updateOrderState(int order_id,int order_state);
	
	public List<OrderVO> orderView(String mb_id,int type);
	public OrderVO orderInfo(int order_id);
}
