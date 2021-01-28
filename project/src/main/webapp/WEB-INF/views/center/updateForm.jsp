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
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href='<c:url value="/board/list"/>'>Notice</a></li>
<li><a href='<c:url value="/fboard/flist"/>'>File Notice</a></li>
<li><a href='<c:url value="/gboard/glist"/>' >Gallery Notice</a></li>
</ul>
</nav>
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->
<article>
<h1>게시물 수정</h1>
<form action="<c:url value="/board/update"/>" method="post">
<input type="hidden" name="num" value="${bb.num }">
<table id="notice">
<tr><td>글쓴이</td><td>${bb.name }</td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pass"></td></tr>
<tr><td>제목</td> <td><input type="text" name="subject" value="${bb.subject }"></td></tr>
<tr><td>글내용</td>
    <td><textarea name="content" rows="10" cols="20">${bb.content }</textarea></td></tr>

</table>
<div id="table_search">
<input type="submit" value="글수정" class="btn" id="Btn">
</div>
</form>


<div class="clear"></div>
<div id="page_control">

</div>
</article>
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>