<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	button{
		width:280px;
		height:60px;
		color:white;
		background-color: #82937F;
		border:0;
	}
	.blockCenter{
		position: absolute;
		top:50%; left:50%;
		width:1000px; height:500px;
		margin-left: -200px;
		margin-top: -200px;
		text-align: center;
		font-size:25px;
		color: #82937F;
	}
</style>
<script>
function formCheck(f){
	if(f.password.value.trim()==""){
		alert("비밀번호를 입력하세요!");
		f.password.value="";
		f.password.focus();
		return false;
	}
	if(!f.password.value.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~,-])|([!,@,#,$,%,^,&,*,?,_,~,-].*[a-zA-Z0-9])/)) {
		alert("비밀번호는 8~16자 영문 대/소문자, 숫자, 특수문자를 사용하세요.")
		f.password.value="";
		f.password.focus();
		return false;
	}
	if(f.password.value.trim() != f.passchk.value.trim()){
		alert("비밀번호가 일치하지 않습니다.");
		f.password.value="";
		f.passchk.value="";
		f.password.focus();
		return false;
	}
	if(f.password.value.trim()==""){
		alert("휴대전화를 입력하세요!");
		f.mobile.value="";
		f.mobile.focus();
		return false;
	}
	if(f.postCode.value.trim()==""){
		alert("주소를 입력하세요!");
		f.postCode.value="";
		f.postCode.focus();
		return false;
	}
	f.submit();
}
</script>
</head>
<body>
<div class="container p-5 my-3">
	<jsp:include page="../menuTop.jsp"></jsp:include>
</div>
<form name="myInformationModify" action="${pageContext.request.contextPath }/myInformationModifyPro.me" method="post">
<div class="container my-4">
	<h1 class="display-4 text-success">회원정보 수정</h1>
</div>
<hr class="greenLine">

<div class="blockCenter">
	<div class="row my-3">
		<div class="col-md-3">
			<label for="memberID">아이디 </label>		
		</div>
		<div class="col-md-3">
			${member.memberID }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="password">비밀번호 </label>	
		</div>
		<div class="col-md-3">
			<input type="password" name="password" id="password" value='${member.password }' />
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="passchk">비밀번호 </label>	
		</div>
		<div class="col-md-3">
			<input type="password" name="passchk" id="passchk" />
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="name">이름 </label>	
		</div>
		<div class="col-md-3">
			${member.name }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="nickname">닉네임 </label>	
		</div>
		<div class="col-md-3">
			${member.nickname }
		</div>
	</div>
	<div class="row my-3">
		<div class="col-md-3">
			<label for="mobile">휴대전화 </label>	
		</div>
		<div class="col-md-3">
			<input type="text" name="mobile" id="mobile" value='${member.mobile }' />
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="postCode">주소 </label>	
		</div>
		<div class="col-md-3">
			<input type="text" name="postCode" id="postCode" value='${member.postCode }'>
		</div>
		<div class="col-md-5">
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="roadAddress"> </label>
		</div>
		<div class="col-md-3">
			<input type="text" name="roadAddress" id="roadAddress" value='${member.roadAddress }'>
		</div>
	</div>
	<div class="row my-1">
		<div class="col-md-3">
			<label for="detailAddress"></label>
		</div>
		<div class="col-md-3">
			<input type="text" name="detailAddress" id="detailAddress" value='${member.detailAddress }'>
		</div>
	</div>
	<div class="row my-5">
		<input type="button" onclick="formCheck(this.form)" value="수정" />&nbsp;&nbsp;
		<button type="reset" >다시작성</button>
	</div>
</div>
</form>
</body>
</html>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
               // document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                }/* else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                }*/ else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>