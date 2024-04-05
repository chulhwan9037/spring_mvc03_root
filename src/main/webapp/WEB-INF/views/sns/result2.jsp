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
			url : "NaverUser.do",
			metgod : "post",
			dataType : "text",
			success : function() {
				let users = data.split("/");
				$("#res").append(users[0]+"("+users[1] +")" + "님 환영합니다.")
				$("#res").append(users[2]+"("+users[3] +")" + "님 환영합니다.")
				$("#res").append(users[4]+"("+users[5] +")" + "님 환영합니다."")
			},
			error : function() {
				alert("읽기실패");
			}
		});
	
	
	});
	function logout_go() {
		location.href = "naverlogout.do"
	}
	
</script>
</head>
<body>
	<h1>NAVER 로그인 결과</h1>
	<div id="res"></div>
	<!-- <a href="kakaoLogout.do">
		<img src="resources/images/kakao_logout.png">
	</a> -->
	
	<input type="button" value="로그아웃" onclick="logout_go()">
	
</body>
</html>
















