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
<style type="text/css">
article {
margin-right: 150px;
}
tr{
border: 1px solid gray;}
td{
border: 1px dashed  gray;
}
</style>
</head>
<body>

<div id="wrap">
<!-- 헤더들어가는 곳 -->
<c:import url = "../inc/top.jsp" charEncoding = "UTF-8" />
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<!-- 왼쪽메뉴 -->



<article>
<h1>회원목록</h1>
<table id="notice" style="width: 780px;">
<tr><td class="tableNum"><b>Name</b></td>
    <td class="tableTitle"><b>Id</b></td>
    <td class="tableUserName"><b>Pass</b></td>
    <td class="tableReadCount"><b>Date</b></td>
    <td class="tableRegDate"><b>Email</b></td>
    <td class="tableRegDate"><b>Address</b></td></tr>

<c:forEach var="mb" items="${mbList}" varStatus="status">

<tr>
<td>${mb.name }</td> <td>${mb.id }</td> <td>${mb.pass }</td>
<td>${mb.date }</td><td>${mb.email }</td> 
<td>${mb.address }<br>${mb.roadAddress }${mb.detailAddress}</td>
</tr>
</c:forEach>
 
 
 
 
 </table>
<div class="clear"></div>
<div id="page_control">

</div>
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