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
    <header>
       <div class="gnb">
            <ul>
            	<li>
                	<form action="" method="get"><!--검색하면 넘어갈 페이지  -->
						<input type="text" name="search" placeholder="검색"><!-- 힌트 -->
						<button type="submit">검색</button>
					</form>
                </li>
                <c:if test="${sessionScope.mb_Classifi=='사업자'}">
	                <li><a href="../customer/settlement.html" alt="">사업자페이지</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_Classifi=='관리자'}">
	                <li><a href="../customer/settlement.html" alt="">관리자페이지</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_id==null}">
	                <li><a href="login.do" alt="">장바구니</a></li>
	                <li><a href="login.do" alt="">마이페이지</a></li>
	                <li><a href="login.do" alt="">로그인</a></li>
	                <li><a href="join.do" alt="">회원가입</a></li>
               	</c:if>
               	<c:if test="${sessionScope.mb_Classifi!=null}">
	                <li><a href="../customer/cart.html" alt="">장바구니</a></li>
	                <li><a href="mypage.do" alt="">마이페이지</a></li>
	                <li><a href="../customer/qna.html" alt="">QnA</a></li>
	                <li><a href="logout.do" alt="">로그아웃</a></li>
	               <li> ${sessionScope.mb_id}님 환영합니다.</li>
               	</c:if>
            </ul>
        </div>
        <div class="logo"> <a href="index.do"> <img src="/web_project/images/logo/logo2.jpg"></a></div>
        <div class="lnb">
            <ul>
            	<c:forEach var="i" items="${catagory}">
            	<li><a href="category.do?category=${i.category_id}">${i.category_id}</a>
		          	<ul class ="menu2_s submenu">
            		<c:forEach var="j" items="${i.getChild()}">
            			<li>
            				<a href="category.do?category=${j.category_id}">${j.category_id}</a>
            			</li>	
            		</c:forEach>
            		</ul>
            	</li>
            	</c:forEach> 
	      	</ul>
        </div>
    </header>
    <div id="content">
       <!--  <div class="section2"> -->
            <div class="hit_product">
                  <br><br>
            	  <p class="hit_title2">이번주 신상품</p>
            	  <p class="hit_title1">NEW ARRIVALS</p><br>
	
            	  <ul>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed1a.jpg">로사 침대 퀸 [그레이빈티지]<br><hr>1,000,000원</a></li>
                    <li><a href="#" alt=""><img src="/web_project/images/bed/bed2a.jpg">로사 침대 퀸 [브라운]<br><hr>900,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed3a.jpg">벨라 침대 퀸 [모던 브라운]<br><hr>1,200,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed4a.jpg">클레어 침대 킹 [그레이 빈티지]<br><hr>1,500,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed1a.jpg">로사 침대 퀸 [그레이빈티지]<br><hr>1,000,000원</a></li>
                    <li><a href="#" alt=""><img src="/web_project/images/bed/bed2a.jpg">로사 침대 퀸 [브라운]<br><hr>900,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed3a.jpg">벨라 침대 퀸 [모던 브라운]<br><hr>1,200,000원</a></li>
                    <li><a href="#" alt=""><img  src="/web_project/images/bed/bed4a.jpg">클레어 침대 킹 [그레이 빈티지]<br><hr>1,500,000원</a></li>
                </ul>
            </div>
        <!-- </div> -->
    </div>
    <br><br>
    <footer>
    	<div class="offiecTop">
    		<div class="office_logo"> <img src="/web_project/images/logo/logo2.jpg"></div>
        <div class="office_adress">
       	신한은행 110-221-461256<br>
       	예금주 : 김세엽 
        <br>(주)Yeopse<br>
        (153-759) 서울시 관악구 신림로 68길 28-6 월드오피스텔 303호</div>
        <div class="shopping_info">
        			<strong>고객만족센터</strong><br>
					☎070-1236-4562<br>
					<strong>평일</strong>오전 09:00 ~ 오후 06:00<br>
					<strong>점심</strong>오후 12:00 ~ 오후 01:00<br>
					휴무일요일 휴무<br>
		</div>
        
    	</div>
        <div class="copyright">COPYRIGHT © 2021 김세엽. ALL RIGHTS RESERVED.</div>
    </footer>
</body>
</html>