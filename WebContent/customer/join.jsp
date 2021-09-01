<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
   
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/joinstyle.css"  type = "text/css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script src="/web_project/script/joinscript.js">

</script>

</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="joinAction.do" method="post" class="signupInfo" name="joinform" onsubmit="return joinCheck();">
    		<fieldset>
    			<legend>회원가입</legend>
    			<input type="hidden" name="hidden_Num" value="0">
    			<table cellspacing="0">
    				<tr>
    					<td>회원구분</td>
    					<td>
    							<input class="firstInput" type="radio" name="mb_classifi" value="일반회원" onclick="changeClassifi();" checked >일반회원
    							<input type="radio" name="mb_classifi" value="사업자" onclick="changeClassifi();">사업자
    					</td>
    				</tr>
    				<tr>
    					<td>*아이디</td>
    					<td>
    						<input class="firstInput"  type="text" name="mb_id" maxlength="20">
    						<input type="button" name="" value="중복확인" onclick="confirmId();">
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
    				<tr>
    					<td id="name">*이름</td>
    					<td>
    						<input class="firstInput"  type="text" name="mb_name" maxlength="20">
    					</td>
    				<tr>
    				<tr>
    					<td id="certifiNum">*주민번호</td>
    					<td>
    						<input class="firstInput" id="mainCertifiNum" type="hidden" name="mb_certifiNum" maxlength="20">
    						<span id="jumin">
    						<input class="firstInput"  type="text" name="mb_certifiNum1" maxlength="6" style="width:50px">
    						-
    						<input type="password" name="mb_certifiNum2" maxlength="7" style="width:60px">
    						</span>
    					</td>
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
    						<input type="hidden" name="mb_phone"">
							
							<input class="firstInput" type="text" name="hp1" maxlength="3"
								style="width:30px">
								-
							<input type="text" name="hp2" maxlength="4"
								style="width:40px">
								-
							<input type="text" name="hp3" maxlength="4"
								style="width:40px">
						</td>
    				<tr>
    				<tr>
    					<td>*이메일</td>
    					<td>
    						<input type="hidden" name="mb_email">
    						<input class="firstInput"  type="text" name="email1" maxlength="20" style="width:70px">
								@
							<input type="text" name="email2" maxlength="20" style="width:80px">
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
    						<input type="submit" value="회원가입">
    						<input type="reset" value="취소"
    							onclick="window.location='index.do'">
    					</td>
    				<tr>
    			</table>
    		</fieldset>
    	</form>
    </div>

    <%@ include file="../index/footer.jsp" %>
</body>
</html>