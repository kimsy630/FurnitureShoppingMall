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
<c:if test="${insertMb==0}">
	<script type="text/javascript">
		alert("회원가입에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
	
<c:if test="${insertMb!=0}">
	<!-- <script type="text/javascript">
		window.location='login.do?insertMb${insertMb}';
	</script> -->
	   <c:redirect url = "login.do">
        <c:param name = "insertMb" value ="${insertMb}"/>
    </c:redirect>
</c:if>
</body>
</html>