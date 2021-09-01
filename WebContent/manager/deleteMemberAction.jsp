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
<c:if test="${deleteM==0}">
	<script type="text/javascript">
		alert("회원탈퇴에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
	
<c:if test="${deleteM!=0}">
	<script type="text/javascript">
		alert("회원탈퇴에 성공했습니다."); 
		window.location='manager_memberInfo.mc';
	</script>
</c:if>
</body>
</html>