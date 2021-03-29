<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='<c:url value="/resources/css/default.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/resources/css/front.css"/>' rel="stylesheet" type="text/css">


</head>
<body>
<div id="wrap">
<!-- 헤더파일들어가는 곳 -->
<c:import url = "../inc/top.jsp" charEncoding = "UTF-8" />
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>
<div id="main_img"><img src='<c:url value="/resources/images/moon/moon1.jpg"/>'
 width="971" height="282"></div>
<!-- 메인이미지 들어가는곳 -->
<article id="front">
<div id="solution">
<div id="hosting">
<h3>Web Hosting Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="security">
<h3>Web Security Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
<div id="payment">
<h3>Web Payment Solution</h3>
<p>Lorem impsun Lorem impsunLorem impsunLorem
 impsunLorem impsunLorem impsunLorem impsunLorem
  impsunLorem impsunLorem impsun....</p>
</div>
</div>
<div class="clear"></div>
<div id="sec_news">
<h3><span class="orange">Security</span> News</h3>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
<dl>
<dt>Vivamus id ligula....</dt>
<dd>Proin quis ante Proin quis anteProin 
quis anteProin quis anteProin quis anteProin 
quis ante......</dd>
</dl>
</div>
</article>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div id="news_notice">
<table class = boardTable>
<thead>
   <tr>
      <td class = tableNum>번호</td>
      <td class = tableTitle>제목</td>
      <td class = tableUserName>작성자</td>
      <td class = tableReadCount>조회수</td>
      <td class = tableRegDate>작성일</td>
   </tr>
</thead>
<tbody>
<c:forEach var="bb" items="${boardList }" end="10">

   <tr>
      <td class = boardNum>${bb.num }</td>
      <td class = boardTitle><a href='<c:url value="/board/content?num=${bb.num}" />'>${bb.subject }</a></td>
      <td class = boardUserName>${bb.name }</td>
      <td class = boardReadCount>${bb.readcount}</td>
	   <td class = tableRegDate>${bb.date}</td>
     
      
   </tr>

	
</c:forEach>

</tbody>
</table>
</div>
<!-- 메인 콘텐츠 들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<c:import url = "../inc/bottom.jsp" charEncoding = "UTF-8" />
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>