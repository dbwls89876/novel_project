<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 시스템 회원가입 페이지</title>
<style>
	table{
	margin:auto;
	width:1000px;
	border:1px solid gray;
	text-align:center;
	}
	.td_title{
	font-weight:bold;
	font-size:x-large;
	}
</style>
<script>
function idCheck(){
	window.open("idCheckForm.jsp","","width=300,height=200");
}
</script>
</head>
<body>
<form name="joinform" action="${pageContext.request.contextPath }/joinForm.me" method="post">
<table>
	<tr>
		<td align="center"><br>
			<jsp:include page="../menuTop.jsp"></jsp:include>
		</td>
	</tr>
</table>
<table border=1>
	<tr>
		<td colspan="2" class="td_title">
		회원가입
		</td>
	</tr>
	<tr>
		<td><label for="memberID">ID : </label></td>
		<td align=left><input type="text" name="memberID" id="memberID" required/>
		<input type="button" value="ID 중복 확인" onclick="idCheck()" /></td>
	</tr>
	<tr>
		<td><label for="password">비밀번호 : </label></td>
		<td align=left><input type="password" name="password" id="password"/></td>
	</tr>
	<tr>
		<td><label for="passwordchk">비밀번호 확인 : </label></td>
		<td align=left><input type="password" name="passwordchk" id="passwordchk" /></td>
	</tr>
	<tr>
		<td><label for="name">이름 : </label></td>
		<td align=left><input type="text" name="name" id="name"/></td>
	</tr>
	<tr>
		<td><label for="nickname">닉네임 : </label></td>
		<td align=left><input type="text" name="nickname" id="nickname"/></td>
	</tr>
	<tr>
		<td><label for="mobile">휴대폰번호 : </label></td>
		<td align=left><input type="text" name="mobile" id="mobile"/>
	</tr>
	<tr >
		<td colspan="2" align="left">
			<input type="text" name = "postCode" id="postCode" placeholder="우편번호" >
			<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
		</td>
	</tr>
	<tr>
		<td colspan="2"><input type="text" id="roadAddress" placeholder="도로명주소"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="text" id="detailAddress" placeholder="상세주소"></td>
	<tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="가입"/>
			<input type="reset" value="다시작성"/>
</table>

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