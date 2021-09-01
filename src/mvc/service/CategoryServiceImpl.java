package mvc.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.persistence.CategoryDAOImpl;
import mvc.vo.CategorysVo;

public class CategoryServiceImpl implements CategoryService{

	@Override
	public void mainMenu(HttpServletRequest req, HttpServletResponse res) {
		CategoryDAOImpl dao=CategoryDAOImpl.getInstance();
		ArrayList<CategorysVo> menu =dao.categoryView(null);
		req.setAttribute("menu", menu);
	}

}
