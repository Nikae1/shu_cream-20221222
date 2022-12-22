<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/SellPage.css" />
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
				<div class="board on">
				<!-- 게시판 글 보기 양식 영역 시작 -->
					<div class="container">
						<div class="row">
							<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
								<thead>
								<tbody>
									<tr>
										<td style ="width: 20%">글 제목</td>
										<td colspan="2"><%=bbs.getBbsTitle() %></td>
									</tr>
									<tr>
										<td>작성자</td>
										<td colspan="2"><%= bbs.getUserId() %></td>
									</tr>
									<tr>
										<td>작성일자</td>
										<td colspan="2"><%= bbs.getBbsDate().substring(0, 11) + bbs.getBbsDate().substring(11, 13)+ "시"
											+ bbs.getBbsDate().substring(14, 16) + "분" %></td>
									</tr>
									<tr>
										<td>내용</td>
										<td colspan="2" style="height: 200px; text-align: left;"><%=bbs.getBbsContent() %></td>
									</tr>
								</tbody>
							</table>
							<a href="bbs.jsp" class="btn btn-primary">목록</a>
							<!-- 해당 글의 작성자가 본인이라면 수정과 삭제가 가능하도록 코드 추가 -->
							<%
								if(userID != null && userID.equals(bbs.getUserId())){
							%>
								<a href="update.jsp?bbsID=<%= bbsID %>" class="btn btn-primary">수정</a>
								<a href="deleteAction.jsp?bbsID=<% bbsID %>" class="btn primary">삭제</a>
								
							<%
								}
							%>
						</div>
					</div>
					<!-- 게시판 글 보기 양식 영역 끝 -->
				</div>
			</div>
		</div>
	</div>

</body>
</html>