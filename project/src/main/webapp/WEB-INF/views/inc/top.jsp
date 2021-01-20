<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<header>
<c:choose>
	<c:when test="${empty sessionScope.id }">
		<div id="login"><a href='<c:url value="/member/login"/> '>로그인</a> | <a href='<c:url value="/member/insert"/>'>회원가입</a></div>
	</c:when>
	
	<c:when test="${sessionScope.id eq 'admin'}">
		<div id="login">${sessionScope.id }님 | <a href='<c:url value="/member/logout"/>'>로그아웃</a> | <a href='<c:url value="/member/update"/>'>회원정보수정</a> | <a href='<c:url value="/member/memberList"/>'>회원목록</a></div>
	</c:when>
	
	<c:otherwise>
		<div id="login">${sessionScope.id }님 | <a href='<c:url value="/member/logout"/>'>로그아웃</a> | 
		<a href='<c:url value="/member/update"/>'>회원정보수정</a></div>
	</c:otherwise>
	
	
</c:choose>


<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><a href='<c:url value="/main/main"/>'><img src='<c:url value="/resources/images/loggo1.png"/>' width="265" height="62" alt="Fun Web"></a></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<ul>
	<li><a href='<c:url value="/main/main"/>'>HOME</a></li>
	<li><a href='<c:url value="/company/welcome"/>'>COMPANY</a></li>
	<li><a href="#">SOLUTIONS</a></li>
	<li><a href='<c:url value="/board/list"/>'>CUSTOMER CENTER</a></li>
	<li><a href="#">CONTACT US</a></li>
</ul>
</nav>
</header>