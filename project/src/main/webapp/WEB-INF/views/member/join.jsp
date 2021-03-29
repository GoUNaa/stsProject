<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='<c:url value="/resources/css/default.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/subpage.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/plus.css"/>' rel="stylesheet" type="text/css">

</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<c:import url = "../inc/top.jsp" charEncoding = "UTF-8" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 본문메인이미지 -->
<div id="sub_img_member"></div>
<!-- 본문메인이미지 -->
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href="#">회원가입</a></li>
<li><a href='<c:url value="/member/login"/> '>Login</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->
<!-- 본문내용 -->
<script src='<c:url value="/resources/script/jquery-3.5.1.js"/>'></script>
<script type="text/javascript">
$(document).ready(function(){
  $('#join').submit(function(){
	if(document.fr.id.value == ""){
		alert("아이디 입력해주세요");
		  document.fr.id.focus();
		  return false; 

	} else if (document.fr.pass.value == "" || document.fr.pass2.value == ""){
		alert("비밀번호를 입력해주세요");
		  document.fr.pass.focus();
		  return false;
		  
	} else if(document.fr.pass.value != document.fr.pass2.value){
		alert("비밀번호가 다릅니다");
		  document.fr.pass.focus();
		  return false;
		  
	} else if(document.fr.name.value == ""){
		alert("이름을 입력해주세요");
		document.fr.name.focus();
		  return false;
		  
	} else if(document.fr.email.value == "" && document.fr.email2.value == ""){
		alert("이메일을 입력해주세요");
		document.fr.email.focus();
		  return false;
		  
	} else if(document.fr.email.value  != document.fr.email2.value){
		alert("이메일을 확인해주세요");
		document.fr.email2.focus();
		  return false;
		  
	}  else if(document.fr.address.value == ""  || document.fr.roadAddress.value =="" || document.fr.jibunAddress.value =="" || document.fr.detailAddress.value ==""){
		alert("주소를 입력해주세요");
		document.fr.address.focus();
		  return false;
	}
	});
	$('.dup').click(function(){
		if(document.fr.id.value == ""){
			alert("아이디 입력해주세요");
			  document.fr.id.focus();
			  return false; 
			  }
		$.ajax('<c:url value="/member/idcheck"/>',{
			data:{id:$('.id').val()},
			success:function(rdata){
				if(rdata=="iddup"){
					rdata="아이디 중복";
				}else if(rdata == "idok"){
					rdata="아이디 사용가능";
				}
				$('#re').html(rdata);
			}
		});
	});
});
</script>




<article>
<h1>회원가입</h1>
<form action='<c:url value="/member/insert"/>' id="join" method="post" name="fr">
<fieldset>
<legend>Basic Info</legend>
<label>User ID</label>
<input type="text" name="id" class="id">
<input type="button" value="dup. check" class="dup" id="dupCheck">
<div id="re"></div><br> <!-- onclick="dupCheck()" -->
<label>Password</label>
<input type="password" name="pass"><br>
<label>Retype Password</label>
<input type="password" name="pass2"><br>
<label>Name</label>
<input type="text" name="name"><br>
<label>E-Mail</label>
<input type="email" name="email"><br>
<label>Retype E-Mail</label>
<input type="email" name="email2"><br>
</fieldset>

<fieldset>
<legend>Optional</legend>
<label>Address</label>
<input type="text" id="sample4_postcode" placeholder="우편번호" name="address">
<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" id="roadAddress" name="roadAddress" placeholder="도로명주소">
<input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소">
<span id="guide" style="color:#999;display:none"></span>
<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("roadAddress").value = roadAddr;
                document.getElementById("jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
<br>


<label>Phone Number</label>
<input type="text" name="phone"><br>
<label>Mobile Phone Number</label>
<input type="text" name="mobile"><br>
</fieldset>
<div class="clear"></div>
<div id="buttons">
<input type="Submit" value="회원가입" class="submit" id = "joinOk">
<input type="reset" value="Cancel" class="cancel" id="joinNo">
</div>
</form>
</article>
<!-- 본문내용 -->
<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<c:import url = "../inc/bottom.jsp" charEncoding = "UTF-8" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>