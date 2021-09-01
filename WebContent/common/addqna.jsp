<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8" />
   
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css">
<link rel="stylesheet" href="/web_project/css/addqna.css"  type = "text/css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script src="/web_project/script/joinscript.js"></script>

</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    <c:if test="${dto==null }">
    
    	<form action="addqnaAction.do" method="post" name="modifyform">
		<input type="hidden" name="b_id" value="${b_id}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
	
		<table align="center" class="maintable"  cellspacing="0">
			<tr>
				<th colspan="2"> 정보를 입력하세요.</th>
			</tr>
			<tr>
				<th style="width:150px">작성자</th>
				<td>
					${writer}
					<input type="hidden" value="${writer}" name="writer">
				</td>
			</tr>
			<tr>
				<th style="width:150px">글제목</th>
				<td>
					<input type="text" name="subject" maxlength="50"  style="width:270px"
					placeholder="글제목을 입력하세요." required>
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea rows="10" cols="50" name="content"
						placeholder="글내용을 입력하세요." word-break:break-all></textarea>
						
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="button" type="submit" value="작성">
					<input class="button" type="reset" value="초기화">
					<input class="button" type="button" value="목록보기"
						onclick="window.location='qna.do?pageNum=${pageNum}'">
					
				</th>
			</tr>
		</table>
	</form>
	</c:if>
    <c:if test="${dto!=null }">
    
    	<form action="updateqnaAction.do" method="post" name="modifyform">
		<input type="hidden" name="b_id" value="${b_id}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
	
		<table align="center" class="maintable"  cellspacing="0">
			<tr>
				<th colspan="2"> 수정할 정보를 입력하세요.</th>
			</tr>
			<tr>
				<th style="width:150px">작성자</th>
				<td>
					${dto.b_writer}
				</td>
			</tr>
			<tr>
				<th style="width:150px">글제목</th>
				<td>
					<input type="text" name="subject" maxlength="50"  style="width:270px"
					placeholder="글제목을 입력하세요." required value="${dto.b_subject}">
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea rows="10" cols="50" name="content"
						placeholder="글내용을 입력하세요." word-break:break-all>${dto.b_content}</textarea>
						
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="button" type="submit" value="수정">
					<input class="button" type="reset" value="초기화">
					<input class="button" type="button" value="목록보기"
						onclick="window.location='qna.do?pageNum=${pageNum}'">
					
				</th>
			</tr>
		</table>
	</form>
	</c:if>	
    </div>

    <%@ include file="../index/footer.jsp" %>
</body>
</html>