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
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<c:import url = "../inc/top.jsp" charEncoding = "UTF-8" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href='<c:url value="/board/list"/>'>Notice</a></li>
<li><a href='<c:url value="/fboard/flist"/>'>File Notice</a></li>
<li><a href='<c:url value="/gboard/glist"/>' >Gallery Notice</a></li>
</ul>
</nav>
<article>
<h1>게시물 삭제</h1>
<form action="<c:url value="/board/delete"/>" method="post">
<input type="hidden" name="num" value="${bb.num }">
<table id="notice">

<tr><td>글번호</td><td>${bb.num }</td> <td>글쓴날짜</td><td>${bb.date }</td></tr>
<tr><td>작성자</td><td>${bb.name }</td><td>조회수</td><td>${bb.readcount }</td></tr>
<tr><td>제목</td><td colspan="3">${bb.subject }</td></tr>
<tr><td>내용</td><td colspan="3">${bb.content }</td></tr>
<tr><td>비밀번호</td><td colspan="3"><input type="password" name="pass"></td></tr>


</table>

<div id="table_search">
<input type="submit" id="Btn" value="삭제" class="btn">
</div>

</form>
</article>



<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<c:import url = "../inc/bottom.jsp" charEncoding = "UTF-8" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>