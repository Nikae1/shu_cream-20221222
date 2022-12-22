<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/SecondPage.css" />
<style>
td {
	text-align: left !important;
}
</style>
</head>
<body>
	<div>
		<div id="Wrap">
			<div id="title">
				<div id="logo" class="image">
					<div class="sselect">
						<select name="ssel" size='1' class='select'>
							<option value='v'>제목</option>
							<option value='w'>사용자</option>
						</select>
					    <input type="search"value="검색">
						<input class="serchb" type="submit" value="검색">
					</div>
					<div class="regist on">등록</div>

					<div class="zzim on">찜</div>
					<div class="end on" onclick="movepage('Login.jsp')">로그인</div>
					<div class="sign on" onclick="movepage('Registration.jsp')">회원가입</div>
				</div>
			</div>
			<div id="screen" class="left" style="position: relative">
				<div class="courie on">택배거래</div>
				<div class="direct on">직거래</div>
				<div class="board on">
					<div id="mainWrapper">
						<ul>
							<!-- 게시판 제목 -->
							<li>게시판</li>

							<!-- 게시판 목록  -->
							<li>
								<ul id="ulTable">
									<li>
										<ul>
											<li>No</li>
											<li>제목</li>
											<li>작성일</li>
											<li>작성자</li>
											<li>조회수</li>
										</ul>
									</li>
									<!-- 게시물이 출력될 영역 -->
									<li>
										<ul>
											<li>1</li>
											<li class="left">제목제목제목제목1</li>
											<li>2014.07.09</li>
											<li>자바킹</li>
											<li>0</li>
										</ul>
									</li>

									<li>
										<ul>
											<li>2</li>
											<li class="left">제목제목제목제목1</li>
											<li>2014.07.09</li>
											<li>자바킹</li>
											<li>0</li>
										</ul>
									</li>

									<li>
										<ul>
											<li>3</li>
											<li class="left">제목제목제목제목1</li>
											<li>2014.07.09</li>
											<li>자바킹</li>
											<li>0</li>
										</ul>
									</li>

									<li>
										<ul>
											<li>4</li>
											<li class="left">제목제목제목제목1</li>
											<li>2014.07.09</li>
											<li>자바킹</li>
											<li>0</li>
										</ul>
									<li>
								</ul>
							</li>

							<!-- 게시판 페이징 영역 -->
							<li>
								<div id="divPaging">
									<div>◀</div>
									<div>1</div>
									<div>2</div>
									<div>3</div>
									<div>4</div>
									<div>5</div>
									<div>▶</div>
								</div>
							</li>

							<!-- 검색 폼 영역 -->
							<li id='liSearchOption'>
								<div>
									<select id='selSearchOption'>
										<option value='T'>제목</option>
										<option value='C'>작성자</option>
									</select> <input id='txtKeyWord' /> <input type='button' value='검색' />
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>