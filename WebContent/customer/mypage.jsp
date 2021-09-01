<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />
<link rel="stylesheet" href="${path}css/style.css" type = "text/css">
<link rel="stylesheet" href="${path}css/mypagestyle.css"  type = "text/css">
<script type="text/javascript">
	function withdrawal(){
		var url="withdrawal.do";
		window.open(url,"withdrawal","menubar=no,width=400,height=200");
	}
</script>
</head>
<body>
	<%@ include file="../index/header.jsp" %>
    <div id="content">
    	<div class="top">
    		<div>
    			Mypage
    		</div>
    		<div>
    			저희 쇼핑몰을 이용해 주셔서 감사합니다. <strong>${sessionScope.mb_id}</strong>님은 <strong>${dto.getRankstr() }</strong> 회원이십니다.
    			<span style="float:right"><button onclick="withdrawal()">회원탈퇴</button></span>
    		</div>
    	</div>
    	<br><br>
    	<div class="point">
    		<div>적립금 </div>
    		<div>${dto.mb_Point } </div>
    		<div>등급 포인트  </div>
    		<div>${dto.mb_Rank_Point } </div>
    	</div>
    	<div class="ranklink">
    		<div class="ranklinkRight">
	    		<div class="ranklinkbox">등급 혜택보기</div>
	    		<table class="ranktable" cellspacing="0">
		    		<tr>
		    			<th>회원 등급</th>
		    			<th>점수 안내</th>
		    			<th>혜택</th>
		    		</tr>
		    		<tr>
		    			<th>LV.1 뉴비	</th>
		    			<td>회원가입 ~ 2,000점</td>
		    			<td>할인 +1%, 적립 +1%</td>
		    		</tr>
					<tr>
		    			<th>LV.2 루키	</th>
		    			<td>2,001 ~ 10,000점</td>
		    			<td>할인 +2%, 적립 +2%</td>
		    		</tr>
					<tr>
		    			<th>LV.3 멤버</th>
		    			<td>10,001 ~ 100,000점</td>
		    			<td>할인 +3%, 적립 +3%</td>
		    		</tr>
		    		<tr>
		    			<th>LV.4 브론즈</th>
		    			<td>100,001 ~ 200,000점</td>
		    			<td>할인 +4%, 적립 +4%</td>
		    		</tr>
					<tr>
		    			<th>LV.5 실버	</th>
		    			<td>200,001 ~ 500,000점</td>
		    			<td>할인 +5%, 적립 +5%</td>
		    		</tr>
		    		<tr>
		    			<th>LV.6 골드	</th>
		    			<td>500,001 ~ 1,000,000점</td>
		    			<td>할인 +6%, 적립 +6%</td>
		    		</tr>
		    		<tr>
		    			<th>LV.7 플래티넘	</th>
		    			<td>1,000,001 ~ 2,000,000점</td>
		    			<td>할인 +7%, 적립 +7%</td>
		    		</tr>
					<tr>
		    			<th>LV.8 다이아몬드	</th>
		    			<td>2,000,001점 ~</td>
		    			<td>할인 +8%, 적립 +8%</td>
		    		</tr>
				</table>
			</div>
    	</div>
    	
    	<div class="mylink">
   			<ul>
   				<li><a href="buy.do">구매내역</a></li>
   				<li><a href="customer_refund.do">환불내역</a></li>
   				<li><a href="memberUpdate.do">회원정보 수정</a></li>
   			</ul>
    	</div>
    </div>
    
	<%@ include file="../index/footer.jsp" %>
</body>
</html>