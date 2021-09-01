<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${insertC==0}">
	<script type="text/javascript">
		alert("장바구니에 담기에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
	
<c:if test="${insertC==1}">
	<script type="text/javascript">
		alert("상품을 장바구니에 담았습니다.");
		window.location='cart.do';
	</script>
</c:if>

<c:if test="${insertC==2}">
	<script type="text/javascript">
		alert("장바구니에 이미 선택한 상품이 있어 수량만 추가합니다.");
		window.location='cart.do';
	</script>
</c:if>

<c:if test="${insertC==3}">
	<script type="text/javascript">
		alert("상품수량을 초과했습니다.");
		window.location='cart.do';
	</script>
</c:if>
</body>
</html>