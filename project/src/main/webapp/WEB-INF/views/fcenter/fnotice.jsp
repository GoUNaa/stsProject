<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<h1>File Notice</h1>
<table id="notice">
<tr>
 <td class = tableNum>번호</td>
      <td class = tableTitle>제목</td>
      <td class = tableUserName>작성자</td>
      <td class = tableReadCount>조회수</td>
      <td class = tableRegDate>작성일</td>
    
  </tr>
<div id="table_search">
<c:if test="${!empty sessionScope.id}">
	<input type="button" value="글쓰기" class="btn" 
    onclick="location.href='<c:url value="/fboard/fwrite"/>'">
</c:if>
</div>

<c:forEach var="fb" items="${fboardList }">

<tr>
<td>${fb.num}</td> <td><a href='<c:url value="/fboard/fcontent?num=${fb.num }"/>'>${fb.subject} 
	<img src='<c:url value="/resources/upload/${fb.file }"/>' width="150" height="150"></a></td>
<td>${fb.name}</td><td>${fb.readcount}</td>
<td><fmt:formatDate value="${fb.date}" type="both" pattern="yyyy.MM.dd"/></td>
</tr>

</c:forEach>
</table>

<div id="a">

<c:if test="${pbBean.startPage > pbBean.pageBlock}">
	<a href='<c:url value="/fboard/flist?pageNum=${pbBean.startPage - pbBean.pageBlock}"/>'>Prev</a>
</c:if>
<c:forEach var="i" begin="${pbBean.startPage}" end="${pbBean.endPage}" step="1">
	<a href='<c:url value="/fboard/flist?pageNum=${i}"/>'>${i}</a>
</c:forEach>
<c:if test="${pbBean.endPage < pbBean.pageCount}">
	<a href='<c:url value="/fboard/flist?pageNum=${pbBean.startPage + pbBean.pageBlock}"/>'>Next</a>
</c:if>

</div>
<div id="table_search">
<form action="fnoticeSearch.jsp" method="post">
<input type="text" name="search" class="input_box"><br>
<input type="submit" value="search" class="btn">
</form>
</div>


<div class="clear"></div>
<div id="page_control">
전체 글 - ${pbBean.count}
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