<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html>
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="/web_project/css/style.css" type = "text/css" >
<link rel="stylesheet" href="/web_project/css/indexstyle.css" type = "text/css" >
</head>
<body>
	<c:if test="${updateMb==0}">
		<script type="text/javascript">
			alert("회원정보 수정에 실패했습니다.");
		</script>
	</c:if>
	<c:if test="${updateMb==1}">
		<script type="text/javascript">
			alert("회원정보 수정을 완료했습니다.");
		</script>
	</c:if>
    <%@ include file="../index/header.jsp" %>
    
    <div id="content">
       <!--  <div class="section2"> -->
            <div class="hit_product">
                  <br><br>
            	  <p class="hit_title2">이번주 신상품</p>
            	  <p class="hit_title1">NEW ARRIVALS</p><br>
            	  <ul>
            	  	<c:forEach var="i" items="${newlist}">
            	  	<li><a href="products.cc?p_id=${i.p_id}" alt=""><img  src="${pathimg}${i.p_image}">${i.p_name}<br><hr>${i.p_price}원</a></li>
                    	
            	  	</c:forEach>
                    <!-- <li><a href="#" alt=""><img  src="/web_project/images/bed/bed1a.jpg">로사 침대 퀸 [그레이빈티지]<br><hr>1,000,000원</a></li>
                    <li><a href="#" alt=""><img src="/web_project/images/bed/bed2a.jpg">로사 침대 퀸 [브라운]<br><hr>900,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed3a.jpg">벨라 침대 퀸 [모던 브라운]<br><hr>1,200,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed4a.jpg">클레어 침대 킹 [그레이 빈티지]<br><hr>1,500,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed1a.jpg">로사 침대 퀸 [그레이빈티지]<br><hr>1,000,000원</a></li>
                    <li><a href="#" alt=""><img src="/web_project/images/bed/bed2a.jpg">로사 침대 퀸 [브라운]<br><hr>900,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed3a.jpg">벨라 침대 퀸 [모던 브라운]<br><hr>1,200,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed4a.jpg">클레어 침대 킹 [그레이 빈티지]<br><hr>1,500,000원</a></li> -->
                </ul>
            </div>
    </div>
    <br><br>
    <%@ include file="../index/footer.jsp" %>
   	
</body>
</html>