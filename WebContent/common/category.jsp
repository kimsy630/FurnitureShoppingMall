<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/categorystyle.css"  type = "text/css">
</head>
<body>
   
    <%@ include file="../index/header.jsp" %>
    
	<c:set var="linkParam" value="${search!=null?('search='+=search):('category='+=category)}"/>
    <div id="content">
		<div class="section1">
			<div class="section1_title">
				<div class="section1_title_img">
				</div>
				<div class="section1_title_text">
					${category}
					<c:if test="${search!=null}">
						<span style="font-size:large; font-weight:bold;">"${search}"</span> 검색 결과  ${cnt}건
					</c:if>
					
				</div>
			</div>
			<div class="section1_menu">
				<ul>
					<c:forEach var="i" items="${categortCountList}">
						<li><a href="category.cc?type=1&category=${i.category_id}" alt="">${i.category_id}(${i.count})</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="section1_sort">
				<ul>
					<li><a href="category.cc?type=1&${linkParam}" >신상품</a></li>
					<li><a href="category.cc?type=6&${linkParam}" alt="">상품명</a></li>
					<li><a href="category.cc?type=5&${linkParam}" alt="">낮은가격</a></li>
					<li><a href="category.cc?type=4&${linkParam}" alt="">높은가격</a></li>
					<li><a href="category.cc?type=2&${linkParam}" alt="">인기상품</a></li>
					<li><a href="category.cc?type=3&${linkParam}" alt="">조회수</a></li>
				</ul>
			</div>
		</div> 
        <div class="section2">
       
            <div class="hit_product">
            	  <ul>
            	  <c:forEach var="i" items="${ dtos}">
                    	<li>
                    	 	<a href="products.cc?p_id=${i.p_id}">
	                    	 	<img  src="${imgpath}${i.p_image}">${i.p_name}<br>
	                    	 	<hr>
	                    	 	
	                    	 	<c:if test="${i.p_count==0}">
	                    	 		<span style="color:red"> sold out </span>
	                    	 	</c:if>
	                    	 	<c:if test="${i.p_count!=0}">
	                    	 		<c:if test="${i.p_saleprice!=0}">
			                    	 	<span style="text-decoration:line-through"> <fmt:formatNumber value="${i.p_price }" pattern="#,###" />원<br> </span>
			                    	 	<span style="color:red"> sale </span>
			                    	 	<fmt:formatNumber value="${i.p_saleprice}" pattern="#,###" />
		                    	 	</c:if>
		                    	 	<c:if test="${i.p_saleprice==0}">
		                    	 		<fmt:formatNumber value="${i.p_price}" pattern="#,###" />원<br>　
		                    	 	</c:if>
	                    	 	</c:if>
                    	 	</a>
                    	</li>
            	  </c:forEach>
                </ul>
            </div> 
        </div>
        <div class="hit_product_pageNumber">
           	<ul>
			<c:if test="${cnt>0}">
				<c:if test="${startPage>pageBlock}">
				<li>
					<a href="category.cc?type=${type}&${linkParam}">[<<]</a>
				</li>
				<li>
					<a href="category.cc?type=${type}.do?pageNum=${startPage-pageBlock}&type=${type}&${linkParam}">[<]</a>
				</li>
				</c:if>
				<c:forEach  var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${i==currentPage}">
					<li>
						<span><b>[${i}]</b></span>
					</li>
					</c:if>
					<c:if test="${i!=currentPage}">
					<li>
						<a href="category.cc?pageNum=${i}&type=${type}&${linkParam}">[${i}]</a>
					</li>
					</c:if>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li>
						<a href="category.cc?pageNum=${startPage+pageBlock}&type=${type}&${linkParam}">[>]</a>
					</li>
					<li>
						<a href="category.cc?pageNum=${pageCount}&type=${type}&${linkParam}">[>>]</a>
				</c:if>
			</c:if>
			</ul>
         </div>
    </div>
    
    <%@ include file="../index/footer.jsp" %>
</body>
</html>