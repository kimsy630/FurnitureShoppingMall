<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="/web_project/css/settlementstyle.css"  type = "text/css">

<title>Insert title here</title>
</head>
<body>
	<div class="main">
		<!-- <div class="topPage">
			<div><a href="index.do"><img src="/web_project/images/manager/home.png"><div>돌아가기</div></a></div>
			<div><a href=""><img src="/web_project/images/manager/manager.png"><div>결산</div></a></div>
			<div><a href=""><img src="/web_project/images/manager/member.png"><div>회원정보</div></a></div>
			<div><a href="productManagement.mc"><img src="/web_project/images/manager/product.png"><div>상품</div></a></div>
			<div><a href="orderApproval.mc"><img src="/web_project/images/manager/cart.png"><div>구매승인</div></a></div>
			<div><a href="refundApproval.mc"><img src="/web_project/images/manager/refund.png"><div>환불승인</div></a></div>
			<div><a href=""><img src="/web_project/images/manager/qna.png"><div>Q&A</div></a></div>
		</div> -->
		<%@ include file="managerHeader.jsp" %>
		<div class="bodyPage">
			<div class="bodyleft">
				<div class="progressMain">
				<c:forEach var="i" items="${list}" varStatus="j">
				<c:set var="date" value="${fn:split(i.date,'-')}"/>
    						
					<div> 
						<c:if test="${!j.last}">
							<div class="progressText">${date[0]}년${date[1]}월</div>
							<progress class="progressbar" value="${i.sum}" max="${listMax}"></progress>
							<div class="progressText2"><fmt:formatNumber value="${i.sum }" pattern="#,###" /></div>
						</c:if>
					</div>
				</c:forEach>
				</div>
			</div>
			
			<div class="bodyright">
				<div class="count">
				 	<div>환불 ${approved==null?0:approved.get(1)}</div>
				 	<div>주문 ${approved==null?0:approved.get(0)}</div>
				 	<div>문의 0</div>
				</div>
				<div class="salesTable">
					<table cellspacing="0" >
						<tr>
							<th>이달매출</th>
							<td><fmt:formatNumber value="${list.get(11).sum}" pattern="#,###" /></td>
						</tr>
						<tr>
							<th>연간매출</th>
							<td><fmt:formatNumber value="${list.get(12).sum}" pattern="#,###" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>