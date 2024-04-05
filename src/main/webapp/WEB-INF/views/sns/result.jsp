<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#res").empty();
	/* 	
		$.ajax({
			url : "kakaoUser.do",
			method : "post",
			dataType : "json",
			success : function(data) {
				console.log(data)
				let name = data["properties"].nickname;
				let email = data["kakao_account"].email;
				$("#res").append(name + "("+email+")" + "님 환영합니다.")
				// let nickname = "";
                let email = "";
                $.each(data, function(index,obj) {
                    if(obj.nickname != undefined){
                     nickname = obj.nickname;
                    }
                    if(obj.email != undefined){
                     email = obj.email;
                    }
                }) //
			},
			error : function() {
				alert("읽기 실패");
			}
		});
		 */
		$.ajax({
			url : "kakaoUser2.do",
			metgod : "post",
			dataType : "text",
			success : function() {
				let users = data.split("/");
				${"#res"}.append(users[1])+"("+users[2] + ")" + "님 환영합니다.")
			},
			error : function() {
				alert("읽기실패");
			}
		});
	
	
	});
</script>
</head>
<body>
	<h1>SNS 로그인 결과</h1>
	<div id="res"></div>
	<!-- <a href="kakaoLogout.do">
		<img src="resources/images/kakao_logout.png">
	</a> -->
	
	<!-- 다음로그아웃 : 내어플리케이션 - 제품설정 - 카카오 로그인 - 고급 -  -->
	<!-- 문서 - 로그인 - REST API - 카카오 계정과 함께 로그아웃 -->
	<!--  -->
	<a
		href="https://kauth.kakao.com/oauth/logout?client_id=76e5525c23d8204aad52b6a00907fb3a&logiut_redirect_uri=http://localhost:8090/kakaologout.do">
		<img src="resources/images/kakao_logout.png">
	</a>
	
</body>
</html>
















