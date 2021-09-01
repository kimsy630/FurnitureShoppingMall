package mvc.persistence;

import java.util.List;

import mvc.vo.AccountVO;

public interface AccountDAO {
	public List<AccountVO> accountMonth(String mb_id);
	
	public List<Integer> approvedCount(String mb_id);

	public List<AccountVO> adminAccountMonth();
	
}
