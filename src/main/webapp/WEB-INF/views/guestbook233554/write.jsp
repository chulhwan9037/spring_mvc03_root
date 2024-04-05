<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록</title>
<!-- summer note  -->
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
a {
	text-decoration: none;
}

table {
	width: 600px;
	border-collapse: collapse;
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	padding: 3px
}

div {
	width: 800px;
	margin: auto;
	text-align: center;
}
</style>
<script type="text/javascript">
		function save(f) {
			// 검사하는 거 만들기
			
			//-------------
			
			f.action = "gb_write_ok.do";
			f.submit();
		}
	</script>
</head>
<body>
	<div>
		<h2>방명록 : 작성화면</h2>
		<hr />
		<p>
			[<a href="gb_list.do">목록으로 이동</a>]
		</p>
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td><input type="text" name="name" size="20" /></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제목</td>
					<td><input type="text" name="subject" size="20" /></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td><input type="text" name="email" size="20" /></td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size="20" /></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<textarea rows="10" cols="60" name="content" id="content"></textarea>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2"><input type="button" value="저장"
							onclick="save(this.form)" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="reset" value="취소" /></td>
					</tr>
				</tfoot>
			</table>
			<input type="hidden" name="cmd" value="save">
		</form>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<script src="resources/js/summernote-lite.js"></script>	
<script src="resources/js/lang/summernote-ko-KR.js"></script>	
<script type="text/javascript">
	$(document).ready(function() {
		$("#content").summernote({
			lang: "ko-KR",										// 한글 설정
			height: 300,              						    // 에디터 높이
			focus: true,            					        // 에디터 로딩후 포커스를 맞출지 여부
			placeholder: '최대3000자까지 쓸 수 있습니다'				//placeholder 설정
			
		});
	});
</script>	
	
	
	
</body>
</html>



















