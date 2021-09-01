<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="${path}css/style.css" type = "text/css">
<link rel="stylesheet" href="${path}css/refundstyle.css"  type = "text/css">
   
</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="#" method="post" class="cartlist">
    		<fieldset>
    			<div class="buytitle">환불상품</div>
    			<table cellspacing="0" class="buyInfo">
    				<tr class="textMenu">
    					<td>이미지</td>
    					<td>상품정보</td>
    					<td>구매일</td>
    					<td>수량</td>
    					<td>합계</td>
    					<td>선택</td>	
    				</tr>
    				<c:forEach var="i" items="${dto}"> 
    				<c:set var="p" value="${i.product}"/>
    					<tr>
    					<td><a href="#"><img src="${path}/images/${p.p_image}"></a></td>
    					<td>${p.p_name}</td>
    					<td><fmt:formatDate pattern="yyy-MM-dd"  value="${i.order_date}"/> </td>
    					<td>${i.order_count}</td>
    					<td><fmt:formatNumber value="${i.order_price}" pattern="#,###" />원</td>
    					<td>
    						<c:if test="${i.order_state==6}">
    							<input type="button" value="환불취소" onclick="window.location='memberRefundCancelAction.do?state=2&order_id=${i.order_id}'">
    						</c:if>
    						<c:if test="${i.order_state==7}">
   								환불 완료
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