<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 - JBLOG</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<link href="<c:url value="/assets/jblog/css/mainstyle.css"/>" rel="stylesheet" type="text/css"/>
<style>
	#loginErrMsg {
		line-height:0.5em;
	}
</style>
</head>
<body style="overflow:hidden;">
<div id="maincontent">
	<header class="mainheader">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
	</header>
	<div class="col-md-4 offset-md-4 col-lg-2 offset-lg-5">
		<form id="login-form" name="loginform" method="POST" action="<c:url value="/users/login"/>">
			<div class="form-group">
				<label class="block-label" for="id">아이디</label> 
				<input class="form-control" id="email" name="id" type="text" value=""> 
			</div>
			<div class="form-group">
				<label class="block-label">패스워드</label> 
				<input class="form-control" name="password" type="password" value="">
			</div>
			<div class="text-center" id="loginErrMsg">
				<c:if test="${ errorMessage != null }">
					<p>로그인 실패</p>
					<p><small>${ errorMessage }</small></p>
				</c:if>
			</div>
			<input class="btn btn-info btn-block" type="submit" value="로그인">
		</form>
	</div>
</div>
</body>
</html>