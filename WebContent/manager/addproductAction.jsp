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
<c:if test="${insertP!=1}">
	<script type="text/javascript">
		alert("상품등록에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
	
<c:if test="${insertP==1}">
	<script type="text/javascript">
		alert("상품을 등록하였습니다.");
		window.location='productManagement.mc';
	</script>
</c:if>
</body>
</html>