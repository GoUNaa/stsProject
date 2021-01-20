<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty sessionScope.id }">
	<c:redirect url="/member/login"/>
</c:if>
<form action ='<c:url value="/member/delete"/>' method="post">
${sessionScope.id }님  <br>회원 탈퇴를 위해 비밀번호를 입력해 주세요 <Br>
<input type="text" name ="pass">	
<input type="submit" value="탈퇴">
</form>	
</body>
</html>