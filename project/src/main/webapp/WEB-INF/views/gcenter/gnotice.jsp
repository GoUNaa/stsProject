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

<Style type="text/css">
#did {text-align : center; font-size:12px;}
</Style>
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
<h1>Gallery Notice</h1>
<div id="table_search">
<c:if test="${!empty sessionScope.id}">
	<input type="button" value="글쓰기" class="btn" 
    onclick="location.href='<c:url value="/fboard/fwrite"/>'">
</c:if>
</div>
<table >
<tr><td style="border-bottom:2px solid #DBDBDB" colspan="4"></td></tr>
<c:set var="newLine" value="0"></c:set>
<c:set var="articleCount" value="0"></c:set>

<c:forEach var="gb" items="${gboardList }">

 <c:if test="${newLine == 0}">
 	<%
 	 out.print("<tr>");
 	%>
 </c:if>
 <c:set var="newLine" value="${newLine+1 }"></c:set>
<c:set var="articleCount" value="${articleCount +1}"></c:set>
	<td>
 	<input type="hidden" value="${gb.num}" name="num" >
 	<a href='<c:url value = "gcontent.jsp?num=${gb.num}" />>' ><img src='<c:url value="/resources/upload/${gb.file }"/>'  width = "150" height="150"><br></a>
 	<Div id="did">${gb.name}<Br><a href='<c:url value="gcontent.jsp?num=${gb.num }" />' >${gb.subject}</a><Br>
 <fmt:formatDate value="${gb.date}" type="both" pattern="yyyy.MM.dd"/></div>
 	
	<Br>
 	</td>
 	</c:forEach>
 	
<c:if test="${newLine == 4}">
	<%
	 out.print("</tr>");
	%>
	<c:set var="newLine">0</c:set>
</c:if>

</table>
<div id="a">
<c:if test="${pbBean.startPage > pbBean.pageBlock}">
	<a href='<c:url value="/gboard/glist?pageNum=${pbBean.startPage - pbBean.pageBlock}"/>'>Prev</a>
</c:if>
<c:forEach var="i" begin="${pbBean.startPage}" end="${pbBean.endPage}" step="1">
	<a href='<c:url value="/gboard/glist?pageNum=${i}"/>'>${i}</a>
</c:forEach>
<c:if test="${pbBean.endPage < pbBean.pageCount}">
	<a href='<c:url value="/gboard/glist?pageNum=${pbBean.startPage + pbBean.pageBlock}"/>'>Next</a>
</c:if>
</div>
<div id="table_search">
<form action="gnoticeSearch.jsp" method="post">
<input type="text" name="search" class="input_box"><br>
<input type="submit" value="search" class="btn" >
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