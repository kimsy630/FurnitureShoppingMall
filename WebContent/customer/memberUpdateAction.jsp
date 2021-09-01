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
<c:if test="${updateMb==1}">
	<script type="text/javascript">
		alert("회원정보수정에 성공했습니다.");
		window.location='index.do';
	</script>
</c:if>
<c:if test="${updateMb==0}">
	<script type="text/javascript">
		alert("회원정보수정에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
</body>
</html>