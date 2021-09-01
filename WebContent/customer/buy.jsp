<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="${path}/css/style.css" type = "text/css">
<link rel="stylesheet" href="${path}/css/buystyle.css"  type = "text/css">
   
</head>
<body>
	
   
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="#" method="post" class="cartlist">
    		<fieldset>
    			<div class="buytitle">구매상품</div>
    			<table cellspacing="0" class="buyInfo">
    				<tr class="textMenu">
    					<td>이미지</td>
    					<td>상품정보</td>
    					<td>구매일</td>
    					<td>배송지</td>
    					<td>수령인</td>
    					<td>수령인 전화번호</td>
    					<td>적립포인트</td>
    					<td>수량</td>
    					<td>구매가</td>
    					<td>선택</td>	
    				</tr>
    				<c:forEach var="i" items="${dto}"> 
    				<c:set var="p" value="${i.product}"/>
    					<tr>
    					<td><a href="../common/products.html"><img  src="${path}/images/${p.p_image}"></a></td>
    					<td>${p.p_name}</td>
    					<td><fmt:formatDate pattern="yyy-MM-dd"  value="${i.order_date}"/> </td>
    					<td>${i.order_address}</td>
    					<td>${i.order_name}</td>
    					<td>${i.order_ph}</td>
    					<td><fmt:formatNumber value="${i.order_point}" pattern="#,###" />P</td>
    					<td>${i.order_count}</td>
    					<td><fmt:formatNumber value="${i.order_price}" pattern="#,###" />원</td>
    					<td>
    						<c:if test="${i.order_state==0}">
    							<input type="button" value="구매 취소" onclick="window.location='orderCancelAction.do?state=5&order_id=${i.order_id}'">
    						</c:if>
    						<c:if test="${i.order_state==1}">
   								<input type="button" value="환불 요청" onclick="window.location='refundAction.do?state=6&order_id=${i.order_id}'">
   								<input type="button" value="구매 확정" onclick="window.location='orderConfirmAction.do?state=2&order_id=${i.order_id}'">
    						</c:if>
    						<c:if test="${i.order_state==2}">
   								<input type="button" value="후기 작성" onclick="window.location='#'">
   								구매완료
   							</c:if>
   							<c:if test="${i.order_state==3}">
   								구매완료
   							</c:if>
    						<c:if test="${i.order_state==4}">
								요청 거절(판매자)
							</c:if>
							<c:if test="${i.order_state==5}">
								구매 취소(고객)
							</c:if>
    					</td>	
    				</tr>
    				</c:forEach>
    			</table>
    		</fieldset>
    	</form>
    </div>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>