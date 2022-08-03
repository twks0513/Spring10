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
	<c:forEach var="dto" items="${list }">
		아이디 : ${dto.id }<br>
		이름 : ${dto.name }<br>
		파일 이름 : ${dto.imgName }<br>
		<img src="download?file=${dto.imgName }" width="100px" height="100px"><br>
		<a href="download?file=${dto.imgName }">다운로드</a><br>
		<a href="delete?file=${dto.imgName }&id=${dto.id }">파일 삭제</a> 
		<hr>
	</c:forEach>
	<a href="form">업로드 이동</a>
</body>
</html>