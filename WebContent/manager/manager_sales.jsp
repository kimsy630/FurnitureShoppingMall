<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="${path2}css/settlementstyle.css"  type = "text/css">
<link rel="stylesheet" href="${path2}css/pmstyle.css"  type = "text/css">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
 <script>
 $(function(){ 
	$(".allCheck").click(function(){
		 if($(".allCheck").prop("checked")) { 
		 $("input[type=checkbox]").prop("checked",true); 
		 } else { 
			 $("input[type=checkbox]").prop("checked",false); 
		} 
	}) 
}) 
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		
		<%@ include file="managerHeader.jsp" %>
		<%-- <div class="topPage">
			<div><button id="home"><img src="${imgpath}manager/home.png"><div>돌아가기</div></button></div>
			<div><button id="manager"><img src="${imgpath}manager/manager.png"><div>결산</div></button></div>
			<div><button id="member"><img src="${imgpath}manager/member.png"><div>회원정보</div></button></div>
			<div><button id="product"><img src="${imgpath}manager/product.png"><div>상품</div></button></div>
			<div><button id="order"><img src="${imgpath}manager/cart.png"><div>구매승인</div></button></div>
			<div><button id="refund"><img src="${imgpath}manager/refund.png"><div>환불승인</div></button></div>
			<div><button id="qnd"><img src="${imgpath}manager/qna.png"><div>Q&A</div></button></div>
		</div> --%>
		<div class="bodyPage">
		<form action="login.html" method="post" class="PMInfo">
    		<fieldset>
    			<div class="PMtitle">
    				<div> 구매승인 </div>
    				<div> </div>
    				<div>
					</div>
    			</div>
    			
    			<div class="scrollTable">
	    			<table cellspacing="0" class="PMtable">
	    				<tr class="textMenu">
	    					<th><input type="hidden" class="allCheck" name="chioce" value="all"></th>
	    					<th>이미지</th>
	    					<th>상품명</th>
	    					<th>구매신청일</th>
	    					<th>구매자</th>
	    					<th>주소</th>
	    					<th>핸드폰</th>
	    					<th>수량</th>
	    					<th>판매가</th>
	    					<th>합계</th>
	    					<th>선택</th>	
	    				</tr>
	    			<c:if test="${dto==null}">
	    				<tr >
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    				</tr>
	    				</c:if>
    				<c:forEach var="i" items="${dto}"> 
    				<c:set var="p" value="${i.product}"/>
    					<tr>
	    					<td><input type="hidden" name="chioce" value="손님 장바구니 코드? 아니면 여기서받은for문 번호"></td>
	    					<td><a href="../common/products.html"><img  src="${imgpath}${p.p_image}"></a></td>
	    					<td>${p.p_name}</td>
	    					<td><fmt:formatDate pattern="yyy-MM-dd"  value="${i.order_date}"/> </td>
	    					<td>${i.order_name}</td>
	    					<td>${i.order_address}</td>
	    					<td>${i.order_ph}</td>
	    					<td>${i.order_count}</td>
	    					<td><fmt:formatNumber value="${i.order_price/i.order_count}" pattern="#,###" />원</td>
	    					<td><fmt:formatNumber value="${i.order_price}" pattern="#,###" />원</td>
	    					<td>
	    					<c:if test="${i.order_state==0}">
	    						<input type="button" value="승인" onclick="window.location='orderConfirmAction.mc?state=1&order_id=${i.order_id}'">
	    						<input type="button" value="취소" onclick="window.location='orderCancelAction.mc?state=4&order_id=${i.order_id}'">
	    					</c:if>
	    					<c:if test="${i.order_state==1}">
	    						승인 완료
	    					</c:if>
	    					<c:if test="${i.order_state==2||i.order_state==3}">
	    						구매 확정
	    					</c:if>
	    					<c:if test="${i.order_state==4}">
	    						승인 취소(판매자)
	    					</c:if>
	    					<c:if test="${i.order_state==5}">
	    						구매 취소(손님)
	    					</c:if>
	    					</td>	
	    				</tr>
    				</c:forEach>
	    			</table>
    			</div>
    			<!-- <div class="submitbutton">
    				<input type="submit" value="상품추가">
	 				<input type="submit" value="선택삭제">	
    			</div> -->
    		</fieldset>
    	</form>
		</div>
	</div>
</body>
</html>
