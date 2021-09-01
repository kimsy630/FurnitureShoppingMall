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
<c:if test="${deleteP==0}">
	<script type="text/javascript">
		alert("상품삭제에 실패했습니다.");
		window.history.back();
	</script>
</c:if>
	
<c:if test="${deleteP!=0}">
	<c:if test="${sessionScope.mb_classifi=='사업자'}">
          <script type="text/javascript">
			alert("상품이 삭제되었습니다.");
			window.location='productManagement.mc';
		</script>
    </c:if>
    <c:if test="${sessionScope.mb_classifi=='관리자'}">
	      <script type="text/javascript">
			alert("상품이 삭제되었습니다.");
			window.location='productAdmin.mc';
		</script>
     </c:if>
</c:if>
</body>
</html>