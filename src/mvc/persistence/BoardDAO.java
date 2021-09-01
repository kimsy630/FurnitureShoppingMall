package mvc.persistence;

import java.util.ArrayList;

import mvc.vo.SearchVO;
import mvc.vo.BoardVO;

public interface BoardDAO {
	public int getBoardCnt(SearchVO search);
	
	//게시글 목록
	public ArrayList<BoardVO>  getBoardList(SearchVO search);
	
	//게시글 상세 페이지, 수정 상세페이지
	public BoardVO getBoardDetail(int b_id);
	
	//게시글 수청- 수정 처리 페이지
	public int updateBoard(BoardVO vo);
	//게시글 쓰기 처리페이지
	public int insertBoard(BoardVO vo);
	//게시글 삭제 처리페이지
	public int deleteBoard(int b_id);
	//게시글 인증- 게시글 삭제 처리페이지
}
