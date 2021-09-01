<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/loginstyle.css"  type = "text/css">
</head>
<body>
<c:if test="${selectMb==1}"><!-- 로그인성공 -->
	<c:redirect url="index.do?selectMb=${selectMb}"/>
</c:if>
<c:if test="${selectMb==0}"><!--없는아이디 로그인 실패  -->
	<script type="text/javascript">
		 alert("존재하지 않는 아이디입니다.\n 확인후 다시 시도하세요!");
	</script>
</c:if>
<c:if test="${selectMb==-1}"><!--비밀번호 틀림  실패  -->
	<script type="text/javascript">
		 alert("비밀번호가 틀렸습니다.\n 확인후 다시 시도하세요!");
	</script>
</c:if>

    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
	 	<c:if test="${param.insertMb==1}">
	 		<div style="text-align: center; width: 100%; max-width: 1200px; margin: 0 auto;" >
				회원가입에 성공하셧습니다.<br>
				로그인해주세요.
			</div>
	 	</c:if>
	 	
    	<form action="loginAction.do" method="post" class="loginInfo">
    		<fieldset>		
    			<div> LOGIN </div>
    			<div>WELLCOM BACK</div>
    			<div><input type="text" name="mb_id" placeholder="  아이디"></div>
    			<div><input type="password" name="mb_pwd" placeholder="  비밀번호"></div>
    			<div> <input type="submit"value="로그인"> </div>
    			<div>
    					<hr>
    					<a href="#">아이디찾기</a>|
						<a href="#">비밀번호찾기</a>|
						<a href="join.do">회원가입</a>
    					<hr>
    			</div> 
    		</fieldset>
    	</form>
    </div>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>