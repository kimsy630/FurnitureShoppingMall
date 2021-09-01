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
<c:if test="${updateP==0}">
	<script type="text/javascript">
		alert("상품의 재고수량이 부족합니다."); 
		window.history.back();
	</script>
</c:if>
	
<c:if test="${updateP!=0}">
	<script type="text/javascript">
		alert("상품구매를 완료했습니다.");
		window.location='index.do';
	</script>
</c:if>
</body>
</html>