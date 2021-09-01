package mvc.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.persistence.BoardDAOImpl;
import mvc.persistence.ProductDAOImpl;
import mvc.vo.BoardVO;
import mvc.vo.CategorysVo;
import mvc.vo.ProductsVO;
import mvc.vo.SearchVO;

public class ProductServiceImpl implements ProductService{
	
	@Override
	public void addProduct(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		  String uploadPath = req.getRealPath("/images");
	      File dir = new File(uploadPath);
	      if (dir.exists() == false) { // upload 폴더 없으면
	         dir.mkdir(); // 폴더 만들기
	      }

		
		int sizeLimit = 1024 * 1024 * 10;  // 업로드 용량

	    MultipartRequest multi = new MultipartRequest(req, uploadPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		
        String mb_id=(String)req.getSession().getAttribute("mb_id");
		String p_name=multi.getParameter("p_name");
		String p_category=multi.getParameter("p_category");
		int p_count=Integer.parseInt(multi.getParameter("p_count"));
		int p_price=Integer.parseInt(multi.getParameter("p_price"));
		int p_saleprice=0;
		if(multi.getParameter("p_saleprice")!=null)
			p_saleprice=Integer.parseInt(multi.getParameter("p_saleprice"));
		
		String p_image= multi.getFilesystemName("p_image1"); 
		String p_image2=multi.getFilesystemName("p_image2");
		String p_image3=multi.getFilesystemName("p_image3");
		
		ProductsVO vo = new ProductsVO();
		vo.setMb_id(mb_id);
		vo.setP_name(p_name);
		vo.setP_category(p_category);
		vo.setP_count(p_count);
		vo.setP_price(p_price);
		vo.setP_saleprice(p_saleprice);
		vo.setP_image(p_image);
		vo.setP_image2(p_image2);
		vo.setP_image3(p_image3);
		vo.setP_date(new Timestamp(System.currentTimeMillis()));
		int insertP=ProductDAOImpl.getInstance().insertProduct(vo);
		
		req.setAttribute("insertP",insertP);
	}

	@Override
	public void ProductSellerList(HttpServletRequest req, HttpServletResponse res) {
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int type=0;
		if(req.getParameter("type")!=null) {
			type=Integer.parseInt(req.getParameter("type"));
		}
		ProductDAOImpl dao=ProductDAOImpl.getInstance();
		List<ProductsVO> dto= dao.productSellerList(mb_id, type);
		req.setAttribute("dto", dto);
	}

	@Override
	public void productCategoryList(HttpServletRequest req, HttpServletResponse res) {
		int pageSize=15;		//한페이지당 출력할 글 갯수
		int pageBlock=5;	//한블럭당 페이지 갯수 
		int cnt	=0;			//글갯수
		int start = 0;  	//현재 페이지 시작 글번호
		int end=0;			//현재 페이지 마지막 글번호
		int number=0;		//출력용 글번호
		String pageNum="";	//페이지 번호
		int currentPage=0;	//현재페이지
		
		int pageCount=0;	//페이지 갯수
		int startPage=0;	//시작페이지
		int endPage=0;		//마지막페이지
		
		int type=1;
		SearchVO search=new SearchVO();
		search.setType(1);
		if(req.getParameter("category")!=null)search.setCategory(req.getParameter("category"));
		if(req.getParameter("type")!=null) search.setType(Integer.parseInt(req.getParameter("type"))); 
		if(req.getParameter("search")!=null) search.setSearch(req.getParameter("search"));
		
		ProductDAOImpl dao=ProductDAOImpl.getInstance();
		//cnt=dao.productCategoryCount(category);
		cnt=dao.productCount(search);
		pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		currentPage = Integer.parseInt(pageNum);
		pageCount=(cnt/pageSize)+(cnt%pageSize>0?1:0);
		start=(currentPage-1)*pageSize+1;
		end=start+pageSize-1;
		number= cnt-(currentPage-1)*pageSize;
		if(cnt>0) {
			search.setStart(start);
			search.setEnd(end);
			//List<ProductsVO> dtos=dao.productCategoryList(start, end, category, type);
			List<ProductsVO> dtos=dao.productList(search);
			req.setAttribute("dtos",dtos);
		}

		startPage=(currentPage/pageBlock)*pageBlock+1;
		
		if(currentPage%pageBlock==0)startPage-=pageBlock;

		endPage=startPage+pageBlock-1;
		if(endPage>pageCount)endPage=pageCount;
		
		List<CategorysVo> categortCountList= dao.categoryCount(search.getCategory());
		
		req.setAttribute("categortCountList",categortCountList);
		
		req.setAttribute("cnt", cnt);//글갯수
		req.setAttribute("number", number);//출력용 글번호
		req.setAttribute("pageNum", pageNum);//페이지 번호
		req.setAttribute("type", type);//검색타입
		
		if(search.getCategory()!=null)req.setAttribute("category", search.getCategory());//페이지 번호
		if(search.getSearch()!=null)req.setAttribute("search", search.getSearch());//페이지 번호

		if(cnt>0) {
			req.setAttribute("startPage", startPage);//시작페이지
			req.setAttribute("endPage", endPage);//마지막페이지
			req.setAttribute("pageBlock", pageBlock);//한 블럭당 페이지 갯수
			req.setAttribute("pageCount", pageCount);//페이지 갯수
			req.setAttribute("currentPage", currentPage);//현제페이지
		}
	}

	@Override
	public void deleteproductAction(HttpServletRequest req, HttpServletResponse res) {
		
	    String[] deleteList =req.getParameterValues("chioce");		
		
		ProductDAOImpl dao= ProductDAOImpl.getInstance();
		
		for(int i=0;i<deleteList.length;i++) {
			System.out.println(deleteList[i]);
			int num = dao.deleteProducte(Integer.parseInt(deleteList[i]));
			System.out.println("num : "+num);
		}
		
		//req.setAttribute("deleteP", );
	}

	@Override
	public void productInfo(HttpServletRequest req, HttpServletResponse res) {
		int p_id=Integer.parseInt( req.getParameter("p_id"));
		ProductDAOImpl dao= ProductDAOImpl.getInstance();
		dao.productReadCount(p_id);
		ProductsVO vo=dao.productDetail(p_id);
		req.setAttribute("vo", vo);
	}

	@Override
	public void updateproductAction(HttpServletRequest req, HttpServletResponse res) throws IOException {
		  String uploadPath = req.getRealPath("/images");
	      File dir = new File(uploadPath);
	      if (dir.exists() == false) { // upload 폴더 없으면
	         dir.mkdir(); // 폴더 만들기
	      }

		
		int sizeLimit = 1024 * 1024 * 10;  // 업로드 용량

	    MultipartRequest multi = new MultipartRequest(req, uploadPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		
		String p_name=multi.getParameter("p_name");
		String p_category=multi.getParameter("p_category");
	    int p_id=Integer.parseInt(multi.getParameter("p_id"));
		int p_count=Integer.parseInt(multi.getParameter("p_count"));
		int p_price=Integer.parseInt(multi.getParameter("p_price"));
		int p_saleprice=Integer.parseInt(multi.getParameter("p_saleprice"));
		
		String p_image="";
		String p_image2="";
		String p_image3="";
		String filename = "";
		 try { 
			 Enumeration files = multi.getFileNames();
			 
			 if(files.hasMoreElements()) {
				 p_image3 = multi.getFilesystemName((String) files.nextElement()); 
			 }
			 if(files.hasMoreElements()) {
				 p_image2 = multi.getFilesystemName((String) files.nextElement()); 
			 }
			 if(files.hasMoreElements()) {
				 p_image = multi.getFilesystemName((String) files.nextElement()); 
			 }
		 } catch(Exception e) {
			e.printStackTrace(); 
		 }
		 
		ProductsVO vo = new ProductsVO();
		vo.setP_id(p_id);
		vo.setP_name(p_name);
		vo.setP_category(p_category);
		vo.setP_count(p_count);
		vo.setP_price(p_price);
		vo.setP_saleprice(p_saleprice);
		vo.setP_image(p_image);
		vo.setP_image2(p_image2);
		vo.setP_image3(p_image3);
		
		int updateP=ProductDAOImpl.getInstance().updateProduct(vo);
		req.setAttribute("updateP",updateP);
	}

	@Override
	public void indexView(HttpServletRequest req, HttpServletResponse res) {
		ProductDAOImpl dao=ProductDAOImpl.getInstance();
		List<ProductsVO>newlist= dao.newProduct();
		
		req.setAttribute("newlist", newlist);
	}

	@Override
	public void productadminList(HttpServletRequest req, HttpServletResponse res) {
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int type=0;
		if(req.getParameter("type")!=null) {
			type=Integer.parseInt(req.getParameter("type"));
		}
		ProductDAOImpl dao=ProductDAOImpl.getInstance();
		List<ProductsVO> dto= dao.productAdminList(type);
		req.setAttribute("dto", dto);
	}
	

}
