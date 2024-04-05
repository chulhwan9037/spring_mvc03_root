<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	/* $(document).ready(function() {
		$("#res").empty();
		$.ajax({
			url : "naverUser.do",
			method : "post",
			dataType : "json",
			success : function(data) {
				let nickname = data["response"].nickname;
				let email = data["response"].email;
				let name = data["response"].name;
				$("#res").append(name + "("+email+")" + "님 환영합니다." + nickname)
				/*
				let nickname = "";
                let email = "";
                $.each(data, function(index,obj) {
                    if(obj.nickname != undefined){
                     nickname = obj.nickname;
                    }
                    if(obj.email != undefined){
                     email = obj.email;
                    }
                })
				//
			},
			error : function() {
				alert("읽기실패");
			}
			
		});
	}); */
</script>	
</head>
<body>
	<!-- <h1>SNS 로그인 결과</h1>
    <div id="res"></div>
    <a href="naverLogout.do">
		<img src="resources/images/naver_logout.png">
	</a> -->
</body>
</html>












