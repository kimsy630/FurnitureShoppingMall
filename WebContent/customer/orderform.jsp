<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="${path2}/css/style.css" type = "text/css">
<link rel="stylesheet" href="${path2}/css/orderstyle.css"  type = "text/css">
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
 <script>
 function pointInput(num,total){
		var point =$("input[name = 'point']").val();
		if(point>num){
			point=$("input[name = 'point']").val(num);
		}
		if(total<point){
			$("input[name = 'point']").val(total);
		}
		var totalnum=total - $("input[name = 'point']").val();
		$('#totalPrice').html("결재금액: "+totalnum+"원");
	}
</script>
</head>
<body>
	
    
    <%@ include file="../index/header.jsp" %>
    <div id="content">
    	<form action="orderAction.do" method="post" class="loginInfo" name="cartform" onsubmit="return orderCheck();">
    		<fieldset>
    			<div class="carttitle">결재상품</div>
    			<table cellspacing="0" class="cartInfo">
    				<tr class="textMenu">
    					<td>이미지</td>
    					<td>상품정보</td>
    					<td>판매가</td>
    					<td>수량</td>
    					<td>적립금</td>
    					<td>포인트</td>
    					<td>회원할인가</td>
    					<td>합계</td>
    				</tr>
    				<c:set var="totalprice" value="0"/>
    				
    				<c:forEach var="vo" items="${dto}">
    					<tr>
	    					<td>
	    						<img src="${imgpath}${vo.p_image}"></td>
	    					<td>
	    						${vo.p_name}
	    					</td>
	    					<td>
	    						<fmt:formatNumber value="${vo.price}" pattern="#,###" />원
	    					</td>
	    					<td>${vo.count}</td>
	    					<td>
	    						<fmt:formatNumber value="${vo.point}" pattern="#,###" />P
	    					</td>
	    					<td>
	    						<fmt:formatNumber value="${vo.point}" pattern="#,###" />P
	    					</td>
	    					<td>
								<fmt:formatNumber value="${(vo.price-vo.saleprice)*vo.count}" pattern="#,###" />원
	    					</td>
	    					<td>
								<fmt:formatNumber value="${vo.totalprice}" pattern="#,###" />원
	    						<input type="hidden" name="id" value="${vo.id}" >
	    						<input type="hidden" name="${vo.id}cart_id" value="${vo.cart_id}" >
	    						<input type="hidden" name="${vo.id}p_id" value="${vo.p_id}" >
	    						<input type="hidden" name="${vo.id}count" value="${vo.count}" >
	    						<input type="hidden" name="${vo.id}price" value="${vo.totalprice}" >
	    						<input type="hidden" name="${vo.id}point" value="${vo.point}" >
								<input type="hidden" name="${vo.id}cart_id" value="${vo.cart_id}" >
								
								<c:set var="totalprice" value="${totalprice+vo.totalprice}"/>
							</td>
	    				</tr>
    				</c:forEach>
    				<tr>
    					<td colspan="9">
    						총가격 : ${totalprice}
    					</td>
    				</tr>
    			</table>
								
    			<br><br><br>
    			<input type="hidden" name="state" value="${state}" >
    			<table class="orderInfo" align="center" cellspacing="0">
    				<tr>
    					<th colspan="2" text-align="center">
    						주문 정보
    					</th>
    				</tr>
    				<tr>
    					<th>주문자</th>
    					<td><input type="text" name="order_name" value="${member.mb_Name}"  maxlength="5" style="width:50px"></td>
    				</tr>
    			
    				<tr>
    					<th>주소</th>
    					<td><input type="text" name="order_address" value="${member.mb_Address}"  maxlength="30" style="width:300px"></td>
    				</tr>
    				<tr>
    					<th>전화번호</th>
    					<td>
    						<input type="hidden" name="order_ph" value="010-9122-2556">
    						<input type="text" name="hp1" value="010"  maxlength="3" style="width:30px">-
    						<input type="text" name="hp2" value="9122"  maxlength="4" style="width:40px">-
    						<input type="text" name="hp3" value="2556" maxlength="4" style="width:40px">
    					</td>
    				</tr>
    				<tr>
    					<th>적립금</th>
    					<td>
    						<input type="number" name="point" value="0" min="0" max="${member.mb_Point}"  maxlength="20" style="width:100px" oninput="pointInput(${member.mb_Point},${totalprice});"> ${member.mb_Point} 사용가능
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2" class="totalprice">
    						<div id="totalPrice">결재금액 : ${totalprice}원</div>
    					</td>
    				</tr>
    				<tr>
						<td colspan="2">
							<input type="submit" value="결재하기">
							<input type="button" value="결재취소" onclick="window.history.back()">
						</td>
    				</tr>
    			</table>
    		</fieldset>
    	</form>
    </div>
    <%@ include file="../index/footer.jsp" %>
</body>
</html>