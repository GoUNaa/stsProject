<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer>
	<hr>
	<div id="copy">
		All contents Copyright 2011 FunWeb 2011 FunWeb Inc. all rights
		reserved<br> Contact mail : zzaaqqqaz@naver.com Tel +82 64 123
		4315 Fax +82 64 123 4321 <br> <a
			href=' <c:url value="/main/map" />' style="text-decoration: none">부산광역시
			부산진구 동천로 109 삼한골든게이트 7층</a>
	</div>
	<div id="social">
		<img src='<c:url value="/resources/images/facebook.gif"/>' width="33"
			height="33" alt="Facebook"> <img
			src='<c:url value="/resources/images/twitter.gif"/>' width="34"
			height="34" alt="Twitter">
		<%
			String id = (String) session.getAttribute("id");
		if (id != null) {
		%><a href='<c:url value="/main/mail" />'><img
			src='<c:url value="/resources/images/mail.png" />' width="34"
			height="34" alt="mail" style="cursor: pointer"></a>
		<%
			}
		%>
	</div>

</footer>

