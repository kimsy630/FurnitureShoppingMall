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
<c:if test="${updateO==0}">
	<script type="text/javascript">
		alert("환불 요청에 실패했습니다."); 
		window.history.back();
	</script>
</c:if>
	
<c:if test="${updateO!=0}">
	<script type="text/javascript">
		alert("환불요청을 성공했습니다.");
		window.location='buy.do';
	</script>
</c:if>
</body>
</html>