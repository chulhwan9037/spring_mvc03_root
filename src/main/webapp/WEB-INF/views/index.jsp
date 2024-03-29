<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function guestbook_go() {
		location.href="gb_list.do";
	}
	function bbs_go() {
		location.href="bbs_list.do";
	}
	function board_go() {
		location.href="board_list.do";
	}
	function shop_go() {
		location.href="shop_list.do";
	}
</script>

</head>
<body>
	<button onclick="guestbook_go()">guestBook</button>
	<button onclick="bbs_go()">bbs게시판</button>
	<button onclick="board_go()">보드게시판</button>
	<button onclick="shop_go()">쇼핑몰</button>
</body>
</html>