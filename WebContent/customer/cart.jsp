<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="${path2}/css/style.css" type = "text/css">
<link rel="stylesheet" href="${path2}/css/cartstyle.css"  type = "text/css">
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
	
	function deleteButton(p_id){
	 	$("input[type=checkbox]").prop("checked",false);
	 	$("input[id="+p_id+"]").prop("checked",true);
	 	$(".loginInfo").attr("action", "deleteCartAction.do");
	 	document.cartform.submit();
     } 
	 function deleteChioceButton(){
		 if($("input:checkbox[name='chioce']:checked").length==0){
			 alert("삭제할 상품을 선택해주세요.");
			 return fales;
		 }

		 	
	 	$(".loginInfo").attr("action", "deleteCartAction.do");
	 	document.cartform.submit();
	  } 
	 
	 function allOrder(){
		 $("input[type=checkbox]").prop("checked",true);
		 document.cartform.submit();
	 }
	 
	 function selectOrder(my){ 
		 $("input[type=checkbox]").prop("checked",false);
		 $("input[id="+my+"]").prop("checked",true);
		 document.cartform.submit();
	 }
</script>
</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="cartOrderView.do" method="post" class="loginInfo" name="cartform">
    		<fieldset>
    			<div class="carttitle">장바구니</div>
    			<table cellspacing="0" class="cartInfo">
    				<tr class="textMenu">
    					<td><input type="checkbox" name="Allchioce" value="all" class="allCheck"></td>
    					<td>이미지</td>
    					<td>상품정보</td>
    					<td>판매가</td>
    					<td>수량</td>
    					<td>적립금</td>
    					<td>포인트</td>
    					<td>회원할인가</td>
    					<td>합계</td>
    					<td>선택</td>	
    				</tr>
    				<c:set var="totalprice" value="0"/>
    				<c:forEach var="j" items="${dto}">
	    				<c:set var="i" value="${j.product}" />
		    			<c:set var="sale_price" value="${0!=i.p_saleprice?i.p_saleprice:i.p_price}"/>
		    			<c:set var="discount_prict" value="${member.getRankCal(sale_price*j.cart_count)}"/>
	    				<c:set var="point" value="${member.getRankCal((sale_price*j.cart_count)-discount_prict)}"/>
	    				<tr>
	    					<td>
	    						<input type="checkbox" name="chioce" value="${j.cart_id}" id="${j.cart_id}">
	    						<input type="hidden" name="${j.cart_id}count" value="${j.cart_count}">
	    						<input type="hidden" name="${j.cart_id}p_id" value="${i.p_id}">
	    						<input type="hidden" name="${j.cart_id}price" value="${sale_price}">
	    						<input type="hidden" name="${j.cart_id}p_name" value="${i.p_name}">
	    						<input type="hidden" name="${j.cart_id}p_image" value="${i.p_image}">
 	    					</td>
	    					<td><a href="products.do?p_id=${i.p_id} }"><img  src="${imgpath}${i.p_image}"></a></td>
	    					<td>${i.p_name}</td>
	    					<td>
	    						<fmt:formatNumber value="${0!=i.p_saleprice?i.p_saleprice:i.p_price}" pattern="#,###" />원
	    					</td>
	    					<td>${j.cart_count}</td>
	    					<td>
	    						<fmt:formatNumber value="${point}" pattern="#,###" />
	    					</td>
	    					<td>
	    						<fmt:formatNumber value="${point}" pattern="#,###" />
	    					</td>
	    					<td>
								<fmt:formatNumber value="${discount_prict}" pattern="#,###" />
	    					</td>
	    					<td>
								<fmt:formatNumber value="${(sale_price)*j.cart_count -discount_prict}" pattern="#,###" />원
								<c:set var="totalprice" value="${totalprice+((sale_price)*j.cart_count -discount_prict)}"/>
							</td>
	    					<td>
	    						<input type="button" value="주문하기"  onclick="selectOrder(${j.cart_id})">
	    						<input type="button" value="선택삭제" onclick="deleteButton(${j.cart_id})">
	    					</td>	
	    				</tr>
    				</c:forEach>
    				<tr>
    					<td colspan="10">
    						총가격 : ${totalprice}
    					</td>
    				</tr>
    				<tr>
    					<td colspan="10">
    						<input type="button" value="전체상품주문" onclick="allOrder()">
    						<input type="submit" value="선택상품주문">
    						<input type="button" value="선택상품삭제" onclick="deleteChioceButton()">
    					</td>
    				</tr>
    			</table>
    			
    		</fieldset>
    	</form>
    </div>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>