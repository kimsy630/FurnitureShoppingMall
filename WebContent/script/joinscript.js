/**
 * 
 */
var is_certifiNum=true;

function orderCheck(){
	var forms= document.cartform;
	forms.order_ph.value=forms.hp1.value+"-"+forms.hp2.value+"-"+forms.hp3.value;
}
function updateCheck(){
	var forms=document.updateform;
	forms.mb_phone.value=forms.hp1.value+"-"+forms.hp2.value+"-"+forms.hp3.value;
	forms.mb_email.value=forms.email1.value+"@"+forms.email2.value;
}

function setId(id){
	//opener 부모
	opener.document.joinform.mb_id.value=id;
	opener.document.joinform.hidden_Num.value=1;
	self.close();
}
function selectEwmailChk(){
	if(document.joinform.email3.value==0){
		document.joinform.email2.value="";
		document.joinform.email2.focus();
	}else{
		document.joinform.email2.value=document.joinform.email3.value;
	}
	//직접입력 - email2초기화
	//직접 입력이 아닌경우 select box의 값(email3)을 email2의 값으로 설정
}
function pwdCheck(){
	if(document.joinform.mb_pwd.value.length<=document.joinform.re_mb_pwd.value.length){
		if(document.joinform.mb_pwd.value==document.joinform.re_mb_pwd.value)
			$("#pwdCheck").html("비밀번호가 일치합니다.");
		else $("#pwdCheck").html("비밀번호가 일치하지 않습니다.");
	}else{
		$("#pwdCheck").html("");
	}
}
function changeClassifi(){
	if(document.joinform.mb_classifi.value=="일반회원"){
		$("#name").html("*이름");
		$("#certifiNum").html("*주민번호");
		$("#jumin").css("display","block");
		$("#mainCertifiNum").prop("type","hidden");
	}else{
		$("#name").html("*사업자/판매자");
		$("#certifiNum").html("*사업자번호");
		$("#jumin").css("display","none");
		$("#mainCertifiNum").prop("type","text");
	}
}
function confirmId(){
	//id값 미입력시
	if(!document.joinform.mb_id.value){
		alert("아이디를 입력하세요.");
		document.joinform.mb_id.focus(); 
		return false;
	}
	var url="corfirmId.do?mb_id="+document.joinform.mb_id.value;
	window.open(url,"confirm","menubar=no,width=400,height=200");
}
function joinCheck(){
	if(!document.joinform.mb_id.value){
		alert("아이디를 입력하세요.");
		document.joinform.mb_id.focus(); 
		return false;
	}
	else if(!document.joinform.mb_pwd.value){
		alert("비밀번호를를 입력하세요.");
		document.joinform.mb_pwd.focus(); 
		return false;
	}
	
	else if(!document.joinform.re_mb_pwd.value){
		alert("비밀번호를 확인하세요.");
		document.joinform.re_mb_pwd.focus(); 
		return false;
	}
	else if(document.joinform.mb_pwd.value!=document.joinform.re_mb_pwd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.joinform.re_mb_pwd.value="";
		document.joinform.re_mb_pwd.focus(); 
		return false;
	}
	else if(!document.joinform.mb_name.value){
		alert("이름을 입력하세요");
		document.joinform.mb_name.focus(); 
		return false;
	}else if(!document.joinform.mb_certifiNum1.value&&document.joinform.mb_classifi.value=="일반회원"){
		alert("주민번호 앞자리를 입력하세요");
		document.joinform.mb_certifiNum1.focus(); 
		return false;
	}else if(!document.joinform.mb_certifiNum2.value&&document.joinform.mb_classifi.value=="일반회원"){
		alert("주민번호 뒷자리를 입력하세요");
		document.joinform.mb_certifiNum2.focus(); 
		return false;
	}
	else if(!document.joinform.mb_address.value){
		alert("주소을 입력하세요");
		document.joinform.mb_address.focus(); 
		return false;
	}
	else if(!document.joinform.hp1.value){
		alert("전화번호를입력하세요");
		document.joinform.hp1.focus(); 
		return false;
	}else if(!document.joinform.hp2.value){
		alert("전화번호를입력하세요");
		document.joinform.hp2.focus(); 
		return false;
	}else if(!document.joinform.hp3.value){
		alert("전화번호를입력하세요");
		document.joinform.hp3.focus(); 
		return false;
	}
	else if(!document.joinform.email1.value){
		alert("이메일을 입력하세요");
		document.joinform.email1.focus(); 
		return false;
	}
	else if(!document.joinform.email2.value&&document.joinform.email3.value=="0"){
		alert("이메일 형식에 일치하지 않습니다.");
		document.joinform.email2.focus(); 
		return false;
	}
	//중복확인 2번
	else if(document.joinform.hidden_Num.value=="0"){
		alert("중복확인을 해주세요.");
		document.joinform.mb_id.focus(); 
		return false;
	}
	var forms=document.joinform;
	if(forms.mb_classifi.value=="일반회원"){
		forms.mb_certifiNum.value=forms.mb_certifiNum1.value+"-"+forms.mb_certifiNum2.value;
	}
	forms.mb_phone.value=forms.hp1.value+"-"+forms.hp2.value+"-"+forms.hp3.value;
	forms.mb_email.value=forms.email1.value+"@"+forms.email2.value;
}