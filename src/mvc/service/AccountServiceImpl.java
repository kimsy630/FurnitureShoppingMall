package mvc.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.AccountDAOImpl;
import mvc.vo.AccountVO;

public class AccountServiceImpl  implements AccountService{

	@Override
	public void accountView(HttpServletRequest req, HttpServletResponse res) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		
		AccountDAOImpl dao = AccountDAOImpl.getInstance();
		List<AccountVO> list =dao.accountMonth(mb_id);
		List<Integer> approved=dao.approvedCount(mb_id);
		
		int listMax = 0;
		for(int i=0;i<list.size()-1;i++) {
			if(listMax<list.get(i).getSum()) {
				listMax=list.get(i).getSum();
			}
		}

		System.out.println(approved.get(0));
		System.out.println(approved.get(1));
		
		req.setAttribute("approved", approved);
		req.setAttribute("listMax", listMax);
		req.setAttribute("list", list);		
	}

	@Override
	public void adminAccountView(HttpServletRequest req, HttpServletResponse res) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		
		AccountDAOImpl dao = AccountDAOImpl.getInstance();
		
		List<AccountVO> list1 =dao.adminAccountMonth();
		List<AccountVO> list2 =dao.accountMonth(mb_id);
		List<AccountVO> list =new ArrayList<AccountVO>();
		
		List<Integer> approved=dao.approvedCount(mb_id);
		
		for(int i=0;i<list1.size();i++) {
			AccountVO vo = new AccountVO();
			vo.setDate(list1.get(i).getDate());
			vo.setSum(list1.get(i).getSum()+list2.get(i).getSum());
			list.add(vo);
		}
		int listMax = 0;
		for(int i=0;i<list.size()-1;i++) {
			if(listMax<list.get(i).getSum()) {
				listMax=list.get(i).getSum();
			}
		}

		req.setAttribute("approved", approved);
		req.setAttribute("listMax", listMax);
		req.setAttribute("list", list);		
	}

}
