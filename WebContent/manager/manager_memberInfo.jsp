
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="${path2}/css/settlementstyle.css"  type = "text/css">
<link rel="stylesheet" href="${path2}/css/pmstyle.css"  type = "text/css">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
 <script>
 $(function(){ 
	$(".allCheck").click(function(){
		 if($(".allCheck").prop("checked")) { 
		 $("input[type=checkbox]").prop("checked",true); 
		 } else { 
			 $("input[type=checkbox]").prop("checked",false); 
		} 
	}) 
}) 
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		
		<%@ include file="managerHeader.jsp" %>
		<div class="bodyPage">
		<form action="login.html" method="post" class="PMInfo">
    		<fieldset>
    			<div class="PMtitle">
    				<div>회원정보 </div>
    				<div></div>
    				<div></div>
    			</div>
    			
    			<div class="scrollTable">
	    			<table cellspacing="0" class="PMtable">
	    				<tr class="textMenu">
	    					<th>회원정보</th>
	    					<th>회원아이디</th>
	    					<th>주소</th>
	    					<th>이름</th>
	    					<th>등급</th>
	    					<th>적립금</th>
	    					<th>등급포인트</th>
	    					<th>휴대폰</th>
	    					<th>선택</th>	
	    				</tr>
	    				<c:forEach var="i" items="${list}">
	    				<tr>
	    					<td>${i.mb_Classifi}</td>
	    					<td>${i.mb_Id}</td>
	    					<td>${i.mb_Address}</td>
	    					<td>${i.mb_Name}</td>
	    					<td>${i.getRankstr()}</td>
	    					<td>${i.mb_Point}</td>
	    					<td>${i.mb_Rank_Point}</td>
	    					<td>${i.mb_Phone}</td>
	    					<td><input type="button" value="탈퇴" onclick="window.location='deleteMemberAction.mc?mb_id=${i.mb_Id}'"></td>	
	    				</tr>
	    				</c:forEach>
	    			</table>
    			</div>
    			<div class="submitbutton">
    				<input type="submit" value="상품추가">
	 				<input type="submit" value="선택삭제">	
    			</div>
    		</fieldset>
    	</form>
		</div>
	</div>
</body>
</html>