<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<%-- <input type="button" value="등록 " onclick="window.location='addqnaAction.do?num=${i.b_id}&pageNum=${pageNum}&ref=${i.b_ref}&ref_step=${i.b_ref_step}&ref_level=${i.b_ref_level}'">
	 --%>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css" >
<link rel="stylesheet" href="/web_project/css/qnastyle.css" type = "text/css" >
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function () {
		$(".qnatable tr").not( "#hidden" ).click(function () {
			var nextTr=$(this).next();
			status = nextTr.css("display"); 
			if (status == "none") { 
				nextTr.css("display", ""); 
			} 
			else { 
				nextTr.css("display", "none"); 
			} 
		}); 
	});
	
	
	
	function addreply(my){
		var nextTr=$(my).parent().parent().next();
		status = nextTr.css("display"); 
		if (status == "none") { 
			nextTr.css("display", ""); 
		} 
		else { 
			nextTr.css("display", "none"); 
		} 
	}
	function addreply2(my){
		var nextTr=$(my).parent().next().next();
		status = nextTr.css("display"); 
		if (status == "none") { 
			nextTr.css("display", ""); 
		} 
		else { 
			nextTr.css("display", "none"); 
		} 
	}
	function updatereply(my){
		var update=$(my).parent().parent().prev();
		status = update.css("display"); 
		if (status == "none") { 
			update.css("display", ""); 
		} 
		else { 
			update.css("display", "none"); 
		} 
		var text=$(my).parent().parent().prev().prev();
		status = text.css("display"); 
		if (status == "none") { 
			text.css("display", ""); 
			my.value="수정";
		} 
		else { 
			my.value="수정취소";
			text.css("display", "none"); 
		} 
	}
	function replybutton(my,my2){
		window.location=my+'&subject=-&content='+$('input[name='+my2+']').val();
	}
	function updatereplybutton(my,my2){
		window.location=my+'&subject=-&content='+$('input[name=update'+my2+']').val();
	}
</script>
</head>
<body>
	<c:if test="${insertCnt==0}">
		<script type="text/javascript">
			alert("실패");
		</script>
	<c:set var="insertCnt" value="-1" />
	</c:if>
	<c:if test="${insertCnt==1}">
		<script type="text/javascript">
			alert("성공");
		</script>
	<c:set var="insertCnt" value="-1" />
	</c:if>
    <%@ include file="../index/header.jsp" %>
    
	<c:set var="searchurl" value="${search!=null?('search='+=search+='&'+='type='+=type):''}"/>
    <form action="qna.do" method="post" name="addqnaform">
    	<input type="hidden" name="content" value="-">
    	<input type="hidden" name="subject" value="-">
	    <div id="content">
	    	<div class="qnaList">
	    		<div class="qnatitle">
	    			<div>Q&A </div>
	    			<input type="button" value="등록 " onclick="window.location='addqna.do?pageNum=${pageNum}'">
	    		</div>
	    		<table cellspacing="0" class="qnatable">
	    			<tr id="hidden" class="trborder">
	    				<td>번호</td>
	    				<td>제목</td>
	    				<td>작성자</td>
	    				<td>작성일</td>
	    			</tr>
	    			<c:forEach var="i" items="${dtos}">
		            	<tr  class="trborder" >
		    				<td>
							${number}
							<c:set var="number" value="${number-1}"/>
							</td>
		    				<td>${i.b_subject}</td>
		    				<td>${i.b_writer}</td>
		    				<td><fmt:formatDate pattern="yyy-MM-dd"  value="${i.b_reg_date }"/></td>
		    			</tr>
		    			<tr id="hidden"  style="display:none;">
		    				<td colspan="4">
		    					<table cellspacing="0" class="replytable" id="hidden">
			            			<tr id="hidden"  class="trtopborder">
			            				<td>${i.b_content}</td>
			            			</tr>
			            			<tr id="hidden" class="trbottomborder">
			            				<td>
				            				<c:if test="${sessionScope.mb_classifi=='관리자' || i.mb_id ==sessionScope.mb_id}">
				            					<input type="button" value="삭제 " onclick="window.location='deleteqna.do?b_id=${i.b_id}&pageNum=${pageNum}'">
				            				</c:if>
			            					<c:if test="${i.mb_id ==sessionScope.mb_id}">
			    								<input type="button" value="수정 " onclick="window.location='updateqna.do?b_id=${i.b_id}&pageNum=${pageNum}'">
					    					</c:if>
					    					<input type="button" value="댓글 " id="addreplybutton" onclick="addreply(this)">
			            				</td>
			            			</tr>
			            			<tr id="hidden"  style="display:none;"  class="trbottomborder">
			            				<td class="addreply">
											<input type="text" name="${i.b_id}">
											<input type="button" value="등록" onclick="replybutton('addqnaAction.do?b_id=${i.b_id}&pageNum=${pageNum}&ref=${i.b_ref}&ref_step=${i.b_ref_step}&ref_level=${i.b_ref_level}',${i.b_id})">
			            				</td>
			            			</tr>
			            			
	    							<c:forEach var="j" items="${i.reply}">
			            			<tr id="hidden"  class="trtopborder" >
			            				<td>
			            					<c:forEach begin="0" end="${j.b_ref_level}">
												&nbsp;
											</c:forEach>
				    						┖＞${j.b_content}
				    						<div style="float: right" >
				    							회원 | ${j.b_writer} | <fmt:formatDate pattern="yyy-MM-dd"  value="${j.b_reg_date }"/>
				    						</div>
			            				</td>
			            			</tr>
			            			<tr id="hidden"  style="display:none;">
			            				<td class="addreply" >
			            					<input type="text" name="update${j.b_id}" value="${j.b_content}">
											<input type="button" value="수정" onclick="updatereplybutton('updateqnaAction.do?b_id=${j.b_id}&pageNum=${pageNum}&ref=${j.b_ref}&ref_step=${j.b_ref_step}&ref_level=${j.b_ref_level}',${j.b_id})">
										</td>
			            			</tr>
			            			<tr id="hidden" class="trbottomborder">
			            				<td>
			            					<c:if test="${sessionScope.mb_classifi=='관리자'||j.mb_id ==sessionScope.mb_id}">
				            					<input type="button" value="삭제 " onclick="window.location='deleteqna.do?b_id=${j.b_id}&pageNum=${pageNum}'">
						    				</c:if>
			            					<c:if test="${j.mb_id ==sessionScope.mb_id}">
			    								<input type="button" value="수정 " onclick="updatereply(this)">
					    					</c:if>
					    					<input type="button" value="댓글 " id="addreplybutton" onclick="addreply(this)">
			            				</td>
			            			</tr>
			            			<tr id="hidden"  style="display:none;">
			            				<td class="addreply">
			            					<input type="text" name="${j.b_id}">
											<input type="button" value="등록" onclick="replybutton('addqnaAction.do?b_id=${j.b_id}&pageNum=${pageNum}&ref=${j.b_ref}&ref_step=${j.b_ref_step}&ref_level=${j.b_ref_level}',${j.b_id})">
										</td>
			            			</tr>
			            			</c:forEach>
			            		</table>
		    				</td>
		    			</tr>
	            	</c:forEach>
	    		</table>
	    		<table align="center" class="pageNumber">
					<tr>
						<c:if test="${cnt>0}">
							<c:if test="${startPage>pageBlock}">
							<td>
								<a href="qna.do?${searchurl}">[<<]</a>
							</td>
							<td>
								<a href="qna.do?pageNum=${startPage-pageBlock}&${searchurl}">[<]</a>
							</td>
							</c:if>
							<c:forEach  var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i==currentPage}">
								<td>
									<span><b>[${i}]</b></span>
								</td>
								</c:if>
								<c:if test="${i!=currentPage}">
								<td>
									<a href="qna.do?pageNum=${i}&${searchurl}">[${i}]</a>
								</td>
								</c:if>
							</c:forEach>
							
							<c:if test="${endPage < pageCount}">
								<td>
									<a href="qna.do?pageNum=${startPage+pageBlock}&${searchurl}">[>]</a>
								</td>
								<td>
									<a href="qna.do?pageNum=${pageCount}&${searchurl}">[>>]</a>
								</td>
							</c:if>
						</c:if>
					</tr>
				</table>
				<div style="text-align: right; width: 100%; max-width: 1000px;">
					<select name="type">
						<option value="1">제목 + 내용</option>
						<option value="2">작성자</option>
					</select>
					<input type="text" name="search">
					<input type="submit" value="검색">
				</div>
	    	</div>
	    </div>
    </form>
    <br><br>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>