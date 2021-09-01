package mvc.persistence;

import java.util.ArrayList;
import mvc.vo.CategorysVo;

public interface CategoryDAO {
	public ArrayList<CategorysVo> categoryView(String search);
	public ArrayList<CategorysVo> categoryCount(String search);
}
