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
		alert("환불취소 요청이 실패했습니다."); 
		window.history.back();
	</script>
</c:if>
	
<c:if test="${updateO!=0}">
	<script type="text/javascript">
		alert("환불요청이 취소 되었습니다.");
		window.location='customer_refund.do';
	</script>
</c:if>
</body>
</html>