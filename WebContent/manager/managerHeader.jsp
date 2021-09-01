<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="/web_project/css/settlementstyle.css"  type = "text/css">

<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.mb_classifi==null}">
	
	<script type="text/javascript">
		alert("로그인 하신후 다시 들어오세요.");
		window.location='index.do';
	</script>
	</c:if>
	<div class="topPage">
		<div><a href="index.do"><img src="/web_project/images/manager/home.png"><div>돌아가기</div></a></div>
		
        <c:if test="${sessionScope.mb_classifi=='관리자'}">
		<div><a href="adminSettlement.mc"><img src="/web_project/images/manager/manager.png"><div>결산</div></a></div>
			<div><a href="manager_memberInfo.mc"><img src="/web_project/images/manager/member.png"><div>회원정보</div></a></div>
			<div><a href="productAdmin.mc"><img src="/web_project/images/manager/product.png"><div>상품</div></a></div>
        </c:if>
        <c:if test="${sessionScope.mb_classifi=='사업자'}">
		<div><a href="settlement.mc"><img src="/web_project/images/manager/manager.png"><div>결산</div></a></div>
			<div><a href="productManagement.mc"><img src="/web_project/images/manager/product.png"><div>상품</div></a></div>
		</c:if>
		<div><a href="orderApproval.mc"><img src="/web_project/images/manager/cart.png"><div>구매승인</div></a></div>
		<div><a href="refundApproval.mc"><img src="/web_project/images/manager/refund.png"><div>환불승인</div></a></div>
		<!-- <div><a href=""><img src="/web_project/images/manager/qna.png"><div>Q&A</div></a></div> -->
	</div>
</body>
</html>