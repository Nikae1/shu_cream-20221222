<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/SecondPage.css" />
</head>
<body>
	<div>
		<div id="Wrap">
			<div id="title">
				<div id="logo" class="left">
					shu-cream
					<div class="search on"></div>
					<div class="searchb on">search</div>
					<div class="regist on">등록</div>

					<div class="zzim on">찜</div>
					<div class="end on" onclick="movepage('Login.jsp')">로그인</div>
					<div class="sign on" onclick="movepage('Registration.jsp')">회원가입</div>
				</div>
			</div>
			<div id="screen" class="right" style="position: relative">
				<div class="courie on">택배거래</div>
				<div class="direct on">직거래</div>
				<div class="board on">
					<table class="table">
						<thead>
						<tr>
							<th>번호</th>
							<th>여부</th>
							<th>상태</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>165</td>
							<td>판매중</td>
							<td>중고A</td>
							<td>텀블럼 팝니다</td>
							<td>주성현</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</body>
</html>