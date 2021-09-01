package mvc.persistence;

import java.util.List;

import mvc.vo.CartVO;
import mvc.vo.OrderFormVO;
import mvc.vo.OrderVO;
import mvc.vo.ProductsVO;

public interface CartDAO {
	public int insertCart(CartVO vo);
	public int deleteCart(int num);
	public int updateCart(String mb_id,int p_id, int count);
	public int selectCart(String mb_id,int p_id);
	public List<CartVO> memberCartList(String mb_id);
	public void OderDeleteCart(List<Integer> vo);
}
