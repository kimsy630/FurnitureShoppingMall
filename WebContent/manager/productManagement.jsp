<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="/web_project/css/settlementstyle.css"  type = "text/css">
<link rel="stylesheet" href="/web_project/css/pmstyle.css"  type = "text/css">
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
	
	function deleteButton(p_id){
	 	$("input[type=checkbox]").prop("checked",false);
	 	$("input[id="+p_id+"]").prop("checked",true);
		 document.productsForm.submit();
     } 
 	function choiceCheck(){
 		if(!$("input[name=chioce]").prop("checked")){
			alert("삭제할 상품을 선택해주세요 ");
			return false;
 		}
 	}
 		

</script>
<title>Insert title here</title>
</head>
<body>
	<div class="main">
		<%@ include file="managerHeader.jsp" %>
		<div class="bodyPage">
		<form action="deleteproductAction.mc" method="post" class="PMInfo" name="productsForm" onsubmit="return choiceCheck()">
    		<fieldset>
    			<div class="PMtitle">
    				<div> 판매상품 </div>
    				<div> <a href="addproducts.mc" class="addlink">상품추가</a> </div>
    				<div>
	    				<input type="text" name="search"  placeholder="검색">
	    				<input type="button" value="검색">
					</div>
    			</div>
    			
    			<div class="scrollTable">
	    			<table cellspacing="0" class="PMtable">
	    				<tr class="textMenu">
	    					<td><input type="checkbox" class="allCheck" name="chioce2" value="all"></td>
	    					<td>이미지</td>
	    					<td>상품명</td>
	    					<td>판매자</td>
	    					<td>카테고리</td>
	    					<td>재고수량</td>
	    					<td>정가</td>
	    					<td>세일가</td>
	    					<td>선택</td>	
	    				</tr>
	    				<c:if test="${dto==null}">
	    				<tr >
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    					<td></td>	
	    				</tr>
	    				</c:if>
	    				<c:forEach var="i" items="${dto}">
	    				<tr>
	    					<td><input type="checkbox" name="chioce" id="${i.p_id}" value="${i.p_id}"></td>
	    					<td><a href="../common/products.html"><img  src="${imgpath }${i.p_image }"></a></td>
	    					<td>${i.p_name }</td>
	    					<td>${i.mb_id}</td>
	    					<td>${i.p_category }</td>
	    					<td>${i.p_count }</td>
	    					<td><fmt:formatNumber value="${i.p_price }" pattern="#,###" /> </td>
	    					<c:if test="${i.p_saleprice==0}">
	    						<td>X</td>
	    					</c:if>
	    					<c:if test="${i.p_saleprice!=0}">
	    						<td><fmt:formatNumber value="${i.p_saleprice }" pattern="#,###" /></td>
	    					</c:if>
	    					<td>
	    						<c:if test="${sessionScope.mb_id==i.mb_id}">
	    							<input type="button" value="수정" onclick="window.location='updateproducts.mc?p_id=${i.p_id}'">
	    						</c:if>
	    						<input type="button" value="삭제" onclick="deleteButton(${i.p_id})">
	    					</td>	
	    				</tr>
	    				</c:forEach>
	    			</table>
    			</div>
    			<div class="submitbutton">
    				<input type="button" value="상품추가"  onclick="window.location='addproducts.mc'" >
	 				<input type="submit" value="선택삭제">	
    			</div>
    		</fieldset>
    	</form>
		</div>
	</div>
</body>
</html>