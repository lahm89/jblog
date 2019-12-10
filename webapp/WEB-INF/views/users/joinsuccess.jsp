<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 - JBLOG</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<link href="<c:url value="/assets/jblog/css/mainstyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body style="overflow:hidden;">
<div id="maincontent">
	<header class="mainheader">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
	</header>
	<div class="text-center" id="joinsuccess">
		<p>“감사합니다. 회원 가입 및 블로그 생성에 성공하셨습니다.“</p>
		<strong><a href="<c:url value="login"/>">로그인하기</a></strong>
	</div>
</div>
</body>
</html>