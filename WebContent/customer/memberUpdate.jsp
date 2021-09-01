<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
   
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/joinstyle.css"  type = "text/css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script src="/web_project/script/joinscript.js"></script>

</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="updateAction.do" method="post" class="signupInfo" name="updateform" onsubmit="return updateCheck();">
    		<fieldset>
    			<legend>회원수정</legend>
    			<input type="hidden" name="hidden_Num" value="0">
    			<table cellspacing="0">
    				<tr>
    					<td>회원구분</td>
    					<td>
    						&nbsp;&nbsp;&nbsp;${dto.mb_Classifi}
    					</td>
    				</tr>
    				<tr>
    					<td>*아이디</td>
    					<td>
    						&nbsp;&nbsp;&nbsp;${dto.mb_Id}
    					</td>
    				<tr>
    				<tr>
    					<td>*비밀번호</td>
    					<td>
    						<input class="firstInput"  type="password" name="mb_pwd" maxlength="20">
    					</td>
    				<tr>
    				<tr>
    					<td>*비밀번호 확인</td>
    					<td>
    						<input class="firstInput"  type="password" name="re_mb_pwd" maxlength="20" oninput="pwdCheck();">
    						<span id="pwdCheck" style="color:blue"></span>
    					</td>
    				<tr>
    				<tr><c:if test="${dto.mb_Classifi=='일반회원'}">
    						<td id="name">*이름</td>
    					</c:if>
    					<c:if test="${dto.mb_Classifi!='일반회원'}">
    						<td id="name">*사업자/판매자</td>
    					</c:if>
    					<td>
    						&nbsp;&nbsp;&nbsp;${dto.mb_Name }
    					</td>
    				<tr>
    				<tr>
    					<c:if test="${dto.mb_Classifi=='일반회원'}">
    					<td id="certifiNum">*주민번호</td>
    					<td>
							<c:set var="certifiArr" value="${fn:split(dto.mb_CertifiNum,'-')}"/>
    						<input class="firstInput" id="mainCertifiNum" type="hidden" name="mb_certifiNum" maxlength="20">
    						<span id="jumin">
    						&nbsp;&nbsp;&nbsp;${certifiArr[0]}-*******
    						</span>
    					</td>>
    					</c:if>
    					<c:if test="${dto.mb_Classifi!='일반회원'}">
    					<td id="certifiNum">*사업자번호</td>
    					<td>
    						<input class="firstInput" id="mainCertifiNum" type="hidden" name="mb_certifiNum" maxlength="20">
    						<span id="jumin">
    						&nbsp;&nbsp;&nbsp;${dto.mb_CertifiNum}
    						</span>
    					</td>>
    					</c:if>
    				<tr>
    				<tr>
    					<td>*주소</td>
    					<td>
    						<input class="firstInput"  type="text" name="mb_address" maxlength="50">
    					</td>
    				<tr>
    				<tr>
    					<td>*전화번호</td>
    					<td>
    						<c:set var="mb_phoneArr" value="${fn:split(dto.mb_Phone,'-')}"/>
    						
    						<input type="hidden" name="mb_phone"">
							
							<input class="firstInput" type="text" name="hp1" maxlength="3"
								style="width:30px" value="${mb_phoneArr[0]}">
								-
							<input type="text" name="hp2" maxlength="4"
								style="width:40px" value="${mb_phoneArr[1]}">
								-
							<input type="text" name="hp3" maxlength="4"
								style="width:40px" value="${mb_phoneArr[2]}">
						</td>
    				<tr>
    				<tr>
    					<td>*이메일</td>
    					<td>
    						<c:set var="emailArr" value="${fn:split(dto.mb_Email,'@')}"/>
    						
    						<input type="hidden" name="mb_email">
    						<input class="firstInput"  type="text" name="email1" maxlength="20" style="width:70px" value="${emailArr[0]}">
								@
							<input type="text" name="email2" maxlength="20" style="width:80px"  value="${emailArr[1]}">
							<select class="input" name="email3" onchange="selectEwmailChk();">
								<option value="0">직접입력</option>
								<option value="naver.com">네이버</option>
								<option value="google.com">구글</option>
								<option value="nate.com">네이트</option>
								<option value="daum.com">다음</option>
							</select>
    					</td>
    				<tr>
    				
    				<tr>
    					<td colspan="2">
    						<input type="submit" value="수정하기">
    						<input type="reset" value="취소"
    							onclick="window.location='mypage.do'">
    					</td>
    				<tr>
    			</table>
    		</fieldset>
    	</form>
    </div>
    
    <%@ include file="../index/footer.jsp" %>
</body>
</html>