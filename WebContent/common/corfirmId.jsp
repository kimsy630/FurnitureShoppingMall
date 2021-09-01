<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/web_project/script/joinscript.js"></script>
<style>
	table{
		margin: 70px auto;
	}
</style>
</head>
<body>
	<%
	int mb = (Integer)request.getAttribute("selectMb");
	String id=(String)request.getAttribute("mb_id");
	%>
<form action="corfirmId.do" method="post" name="confirmform">
	<%
	if(mb==1){
	%>
	<table>
		<tr>
			<td colspan="2">
				<span><%=id %></span>는 사용할 수 없습니다.
			</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>
				<input class="input" type="text" name="mb_id" maxlength="20"
				style="width:150px" autofocus required>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<input class="inputButton" type="submit" value="확인">
				<input class="inputButton" type="reset" value="취소" onclick="self.close()">
			</th>
		</tr>
	</table>
	
	<%}else{%>
	<table>
		<tr>
			<td align="center">
				<span><%=id %></span>사용가능한 아이디입니다.
			</td>
		</tr>
		<tr>
			<th>
				<input class="inputButton" type="submit" value="확인" onclick="setId('<%=id%>')">
			</th>
		</tr>
	</table>
	
	<%}%>
</form>
</body>
</html>