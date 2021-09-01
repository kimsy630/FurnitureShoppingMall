package mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {
	public void accountView(HttpServletRequest req, HttpServletResponse res);
	
	public void adminAccountView(HttpServletRequest req, HttpServletResponse res);
}
