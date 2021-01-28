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
<!-- 왼쪽메뉴 -->
<nav id="sub_menu">
<ul>
<li><a href='<c:url value="/board/list"/>'>Notice</a></li>
<li><a href='<c:url value="/fboard/flist"/>'>File Notice</a></li>
<li><a href='<c:url value="/gboard/glist"/>' >Gallery Notice</a></li>
</ul>
</nav>
<article>
<h1>File Content</h1>
<table id="notice">

<tr><td>글번호</td><td>${fb.num }</td> <td>글쓴날짜</td><td>${fb.date }</td></tr>
<tr><td>작성자</td><td>${fb.name }</td><td>조회수</td><td>${fb.readcount }</td></tr>
<tr><td>제목</td><td colspan="3">${fb.subject }</td></tr>
<tr><td>파일</td><td colspan="3"><a href='<c:url value="/resources/upload/${fb.file }" />'>
								<img src='<c:url value="/resources/upload/${fb.file }"/> ' width="100" height="100"></a>${fb.file}</td></tr>
<tr><td>내용</td><td colspan="3">${fb.content }</td></tr>
<tr><td>다운로드</td><td colspan="3"><a href='<c:url value="/fboard/filedown?file_name=${fb.file }" />'>download:${fb.file}</a></td></tr>
	


<div id="table_search">
<c:if test="${! empty sessionScope.id }">
   <c:if test="${sessionScope.id eq fb.name}">
      <input type="button" value="글수정" class="btn" id="Btn" onclick="location.href='<c:url value="/fboard/fupdate?num=${fb.num}"/>'">
      <input type="button" value="글삭제" class="btn" id="Btn" onclick="location.href='<c:url value="/fboard/fdelete?num=${fb.num}"/>'">
   </c:if>
</c:if>
<input type="button" value="글목록" class="btn" id="Btn" onclick="location.href='<c:url value="/fboard/flist"/>'">
</div>

</table>
</form>
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