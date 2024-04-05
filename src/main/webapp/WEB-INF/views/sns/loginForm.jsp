<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
	function kakao_map01() {
		location.href="kakaomap01.do";
	}
	
	function kakao_map02() {
		location.href="kakaomap02.do";
	}
	
	function kakao_map03() {
		location.href="kakaomap03.do";
	}
	
	function kakao_map04() {
		location.href="kakaomap04.do";
	}
	
	function kakao_addr() {
		location.href="kakaoaddr.do";
	}
	
</script>

</head>
<body>
	<h1>SNS 로그인</h1>
	<%-- 
		kakao 로그인
		1. https://developers.kakao.com/ 접속 로그인 하기
		2. 내 어플리케이션에서 어플리케이션 추가
			- 이미지 추가 - 비즈니스 - 개인 개발자 비즈 앱 (Email까지 포함)
			- REST API 키 복사 : 0fa70f7227d7cf37af4189a7ca7b3cda      
			- 플랫폼 설정하기 : IOS, android, web 중 선택
				- 사이트 도메인 : http://localhost:8090
							  원래는 https://도메인, https://사이트IP주소
			- Redirect URI 등록 :
			  1. 활성화 설정 on
			  2. http://localhost:8090/kakaologin.do
			  카카오에서 내 사이트로 결과들을 보내는 주소
		3. 문서 - 카카오 로그인 - 이해하기 (그림)
						   - REST API - 리소스 다운로드하기 (해당 그림 하나 선택 - resources - images)
		4. 카카오 로그인 요청 - 인가코드 받기 요청
	 --%>
	<a
		href="https://kauth.kakao.com/oauth/authorize?client_id=76e5525c23d8204aad52b6a00907fb3a&redirect_uri=http://localhost:8090/kakaologin.do&response_type=code">
		<img src="resources/images/kakao_login_medium.png">
	</a>
	<a
		href="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=mwaKBFts3znxiC9xP4xy&redirect_uri=http://localhost:8090/naverlogin.do&state=1111">
		<img src="resources/images/naver_btn.png">
	</a>
	
	<hr>
	<button type="button" onclick="kakao_map01()">카카오 지도 연습 01</button>	
	<button type="button" onclick="kakao_map02()">카카오 지도 연습 02</button>	
	<button type="button" onclick="kakao_map03()">카카오 지도 연습 03</button>	
	<button type="button" onclick="kakao_map04()">카카오 지도 연습 04</button>	
	<button type="button" onclick="kakao_addr()">다음주소API</button>	
	
	
	
</body>
</html>















