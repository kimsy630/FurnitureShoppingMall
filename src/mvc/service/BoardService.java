package mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	public void boardList(HttpServletRequest req, HttpServletResponse res);
	
	//게시글 수정 페이지
	public void updateView(HttpServletRequest req, HttpServletResponse res);
	
	public void updateAction(HttpServletRequest req, HttpServletResponse res);
	
	public void addQna(HttpServletRequest req, HttpServletResponse res);
	//게시글 쓰기 처리페이지
	public void addQnaAction(HttpServletRequest req, HttpServletResponse res);
	//게시글 삭제 처리페이지
	
	public void deleteQnaAction(HttpServletRequest req, HttpServletResponse res);
}
