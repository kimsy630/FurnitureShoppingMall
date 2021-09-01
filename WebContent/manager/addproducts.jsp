<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="/web_project/css/settlementstyle.css"  type = "text/css">
<link rel="stylesheet" href="/web_project/css/addproductstyle.css"  type = "text/css">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script> 
<script type="text/javascript">
	var maincategorys = new Map();
	var list=
	maincategorys.set("소파탁자","거실가구");
	maincategorys.set("소파","거실가구");
	maincategorys.set("의자","주방가구");
	maincategorys.set("식탁","주방가구");
	maincategorys.set("침대","침실가구");
	maincategorys.set("화장대","침실가구");
	function selectCategory(){
		document.myForm.p_category.options.length=0;
		for(var [key,value] of maincategorys){
			if(document.myForm.mainCategory.value==value){
				document.myForm.p_category.options[document.myForm.p_category.length]=new Option(key,key);
			}
		}
	}
	function sale(){
		var price=$("input[name = 'p_price']").val();
		var saleprice=$("input[name = 'p_saleprice']").val();
		var rate=((price-saleprice)/price)*100;
		if(rate<0){
			
			var num=parseInt(saleprice/10);
			$("input[name = 'p_saleprice']").val(""+num);
		}else if(price&&saleprice){
			$('#saletext').html("할인율 : "+parseInt(rate)+"%");
		}else{
			$('#saletext').html("");
		}
	}
	

	function addproductCheck(){
		if(!document.myForm.mainCategory.value){
			alert("대분류를 선택하세요");
			document.myForm.mainCategory.focus(); 
			return false;
		}else if(!document.myForm.p_category.value){
			alert("소분류를 선택하세요");
			document.myForm.p_category.focus(); 
			return false;
		}else if(!document.myForm.p_name.value){
			alert("상품명을 입력하세요");
			document.myForm.p_name.focus(); 
			return false;
		}else if(!document.myForm.p_count.value){
			alert("수량을 입력하세요");
			document.myForm.p_count.focus(); 
			return false;
		}else if(!document.myForm.p_price.value){
			alert("가격을 입력하세요");
			document.myForm.p_price.focus(); 
			return false;
		}else if(!document.myForm.p_image1.value){
			alert("이미지 파일을 선택하세요");
			document.myForm.p_image1.focus(); 
			return false;
		}else if(!document.myForm.p_image3.value){
			alert("상세설명 이미지 파일을 선택하세요 ");
			document.myForm.p_image3.focus(); 
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
			<form action="addproductAction.mc" enctype="multipart/form-data"  method="post" class="add" name="myForm" onsubmit="return addproductCheck()">
    		<div class="addtitle">재고추가</div>
    		<fieldset>
    			<table class="addtable"  cellspacing="0">
    				<tr class="category">
    					<td>카테고리</td>
    					<td>
    						<select name="mainCategory" onchange="selectCategory();">
    							<option value="">대분류를 선택하세요</option>
    							<option value="침실가구">침실가구</option>
    							<option value="주방가구">주방가구</option>
    							<option value="거실가구">거실가구</option>
    						</select>
    						<select name="p_category">
    							<option value="">대분류를 선택하세요</option>
    						</select>
    					</td>
    				</tr>
    				<tr class="name">
    					<td>상품명</td>
    					<td><input type="text" name="p_name" placeholder="상품명"></td>
    				</tr>
    				<tr class="count">
    					<td>수량</td>
    					<td><input type="number" name="p_count" min="0" placeholder="수량"></td>
    				</tr>
    				<tr class="price">
    					<td>가격</td>
    					<td><input type="number" name="p_price" min="0" placeholder="가격"></td>
    				</tr>
    				<tr class="saleprice">
    					<td>세일 가격</td>
    					<td><input type="number" name="p_saleprice" value="0" min="0" placeholder="세일가격" oninput="sale();"><div id="saletext">0이면 정가</div></td>
    				</tr>
    				<tr class="imgfile">
    					<td>이미지</td>
    					<td><input type="file" name="p_image1"  placeholder="이미지"></td>
    				</tr>
    				<tr class="imgfile">
    					<td>이미지2</td>
    					<td><input type="file" name="p_image2" placeholder="이미지2"></td>
    				</tr>
    				<tr class="imgfile">
    					<td>상세설명 이미지</td>
    					<td><input type="file" name="p_image3" placeholder="상세설명 이미지"></td>
    				</tr>
    				<tr>
    					<td colspan="2">
    						?<%=application.getRealPath("/images/") %> 
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center">
    						<input type="submit" value="등록">
    						<input type="reset" value="취소">
    						<input type="button" value="돌아가기" onclick="window.location='productManagement.mc'">
    					</td>
    				</tr>
    			</table>
    		</fieldset>
    	</form>
		</div>
	</div>
</body>
</html>