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
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href='<c:url value="/board/list"/>'>Notice</a></li>
<li><a href='<c:url value="/fboard/flist"/>'>File Notice</a></li>
<li><a href='<c:url value="/gboard/glist"/>' >Gallery Notice</a></li>
</ul>
</nav>
<article>
<h1>Gallery Content</h1>
<table id="notice">

<tr><td>글번호</td><td>${gb.num}</td> <td>글쓴날짜</td><td>${gb.date }</td></tr>
<tr><td>작성자</td><td>${gb.name }</td><td>조회수</td><td>${gb.readcount }</td></tr>
<tr><td>제목</td><td colspan="3">${gb.subject }</td></tr>
<tr><td>내용</td><td colspan="3"><a href='<c:url value="/resources/uploads/${gb.file }"/>'><img src='<c:url value="/resources/uploads/${gb.file }" />'  width="400" height="400"></a><Br>
<br>
${gb.content}
</td></tr>

<div id="table_search">
<c:if test="${! empty sessionScope.id }">
   <c:if test="${sessionScope.id eq gb.name}">
      <input type="button" value="글수정" class="btn" onclick="location.href='<c:url value="/gboard/gupdate?num=${gb.num}"/>'">
      <input type="button" value="글삭제" class="btn" onclick="location.href='<c:url value="/gboard/gdelete?num=${gb.num}"/>'">
   </c:if>
</c:if>
<input type="button" value="글목록" class="btn" onclick="location.href='<c:url value="/gboard/glist"/>'">
</div>


</table>
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