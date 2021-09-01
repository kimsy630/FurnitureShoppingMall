<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function deleteMember(){
	opener.window.location="withdrawalAction.do";
	self.close();
}
</script>
</head>
<body>
<form action="withdrawalPwdCheck.do" method="post" name="confirmform">
	<c:if test="${selectM==0}">
	<table style="margin: 0 auto">
		<tr>
			<td colspan="2"  align="center">
				비밀번호를 입력해 주세요
			</td>
		</tr>
		<tr >
			<td  align="center">
				<input class="input" type="text" name="mb_pwd" maxlength="20"
				style="width:150px" autofocus required>
			</td>
		</tr>
		<tr  align="center">
			<th colspan="2">
				<input type="submit" value="확인">
				<input type="reset" value="취소" onclick="self.close()">
			</th>
		</tr>
	</table>
	</c:if>
	<c:if test="${selectM!=0}">
	<table style="margin: 0 auto">
		<tr>
			<td align="center">
				정말 회원 탈퇴를 하시겠습니까
			</td>
		</tr>
		<tr>
			<th  align="center">
				<input type="button" value="확인" onclick="deleteMember()">
				<input type="button" value="취소" onclick="self.close()">
			</th>
		</tr>
	</table>
	</c:if>
</body>
</html>