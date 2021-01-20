<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>메일 보내기</title>
<style>
	table{
	text-align:center;
		width : 450px;
		margin : auto;
		color: #asd;
	}
	h1{
		text-align: center;
	}
	td{
		border : 1px solid #333;
	}
</style>
</head>
<body>
<%
%>
<form action= '<c:url value="/main/mail" />' method="POST">
<h1>문의메일 보내기</h1>
<table>
	<tr><td>제목  </td><td><input type="text" name="subject"></td></tr>
	<tr>
		<td>내용  </td>
		<td><textarea name="content" cols=40 rows=20></textarea></td>
	</tr>
	<tr><td align=center colspan=2><input type="submit" value="보내기"></td></tr>
</table>
</form>
</body>
</html>
