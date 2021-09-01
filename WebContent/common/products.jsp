<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "setting.jsp" %>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/products.css"  type = "text/css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script type="text/javascript">
	function addCart(p_id){
		window.location="addcart.do?p_id="+p_id+"&count="+document.productform.count.value+"&p_count="+document.productform.p_count.value;
	}
	function countCheck(maxCount){
		if(document.productform.count.value>maxCount){
			alert("상품 최대수량은"+maxCount+"개입니다.")
			document.productform.count.value=maxCount;
		}
	}
</script>

</head>
<body>
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<div class="mainproduct">
    		<div class="productimg">
    			<table>
    				<tr class="mainimg">
    					<td><img src="${imgpath}${vo.p_image}"></td>
    				</tr>
    				<tr class="imgs">
    					<td>
    						<ul>
    							<li><img src="${imgpath}${vo.p_image}"><li>
    							<li><img src="${imgpath}${vo.p_image2}"><li>
    							<!-- <li><img src=""><li>
    							<li><img src="../images/bed/bed1.png"><li> -->
    						</ul>
    					</td>
    				</tr>
    			</table>
    		</div>
    		<div class="productinfo">
    		<form name="productform" action="noworderform.do" method="post">
    			<table cellspacing="0">
    				<tr class="product_name">
    					<td colspan="2">${vo.p_name}</td>
    				</tr>
    				<tr class="product_pirce">
    					<td>판매가</td>
    					<td>
   							<c:if test="${vo.p_saleprice!=0}">
	                    	 	<span style="text-decoration:line-through; color:black"> <fmt:formatNumber value="${vo.p_price }" pattern="#,###" />원<br> </span>
	                    	 	<span style="color:red"> sale </span>
	                    	 	<fmt:formatNumber value="${vo.p_saleprice}" pattern="#,###" />원
                    	 	</c:if>
                    	 	<c:if test="${vo.p_saleprice==0}">
                    	 		<fmt:formatNumber value="${vo.p_price}" pattern="#,###" />원
                    	 	</c:if>
    					</td>
    				</tr>
    				<tr class="product_tr_base">
    					<td>배송방법</td>
    					<td>직접배송</td>
    				</tr>
    				<tr class="product_tr_base"> 
    					<td>배송비</td>
    					<td>무료</td>
    				</tr>
    				<tr class="product_tr_base">
    					<td>판매자</td>
    					<td>강준우</td>
    				</tr>
    				<tr class="product_count">
    					<td colspan="2">
    						${vo.p_name}
							<input type="number" name="count" class="product_count2"value="1" min="1" max="${vo.p_count}" oninput="countCheck(${vo.p_count});">
    					</td>
    				</tr>
    				<tr class="product_salepirce">
    					<td>총 상품금액</td>
    					<td>
    						<input type="hidden" value="${vo.p_id}" name="p_id">
    						<fmt:formatNumber value="${0==vo.p_saleprice?vo.p_price:vo.p_saleprice}" pattern="#,###" />원
						</td>
    				</tr>
    				<tr class="product_button">
    					<td colspan="2">
    					<c:if test="${sessionScope.mb_id!=null}">
    						<input type="button" value="장바구니" onclick="addCart(${vo.p_id})">
    						<input type="submit" value="바로 구매하기">
    					</c:if>
    					<c:if test="${sessionScope.mb_id==null}">
    						<input type="button" value="장바구니" onclick="window.location='login.do'">
    						<input type="button" value="바로 구매하기"  onclick="window.location='login.do'">
    					</c:if>
    					</td>
    				</tr>
    			</table>
    			<input type="hidden" name="p_count" value="${vo.p_count}">
    			<input type="hidden" name="p_name" value="${vo.p_name}">
    			<input type="hidden" name="p_image" value="${vo.p_image}">
    			<input type="hidden" name="price" value="${0<vo.p_saleprice?vo.p_saleprice:vo.p_price}">
    		</form>
    		</div>
    	</div>
    	<div class="detail">
    		<img src="${imgpath}${vo.p_image3}">
    	</div>
    	<!-- <div class="review">
    		
    	<form action="#" method="post">
    		<div class="addreview">
    				<button href="#">후기작성</button>
    				<input type="submit" value="후기작성" formaction="#">
    		</div>
    		<div class="reviewMain">
    			<div class="rv_top">
    				<span class="member_id">kim</span>
    				<span class="review_title">너무 좋아요</span>
    					<span class="score">★ ★ ★ ★ ★</span>
   				</div>
    			<div class="rv_Content">
    				<span>
    					<img src="../images/bed/bed1a.jpg">
    				</span>
    				<span> 
	    				너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무
	    				좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아
	    				요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너
	    				요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너
    					너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무
	    				좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아
	    				요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너무좋아요너
    				</span>
    			</div>
    			<div class="rv_control">
    				<span class="review_udate"><input formaction="#" type="submit" value="수정"></span>
    				<span class="review_del"><input formaction="#" type="submit" value="삭제"></span>
    				<span class="review_date">2021.01.09</span>
    				
    			</div>
    			<div class="reply_Add">	
    				<span class="reply_AddButton"><input formaction="#" type="submit" value="댓글 작성"></span>
    				<span class="reply_text"><input type="text" name="reply_content"></span>
	    		</div>
    			<div class="reply_list">
    				<span class="reply_Content">└>인정</span>
					<span class="reply_Info">회원 | kais12 &nbsp;|&nbsp; 2020.09.11 19:53 </span>
    			</div>
    			<div class="reply_list">
    				<span class="reply_Content">└>진짜 최고에요</span>
					<span class="reply_Info">회원 | yasw2 &nbsp;|&nbsp; 2020.09.11 19:53 </span>
    			</div>
    		</div>
    	</form>
    	</div> -->
    </div>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>