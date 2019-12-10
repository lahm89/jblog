<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ blogVo.blogTitle } - JBLOG</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<link href="<c:url value="/assets/jblog/css/mainstyle.css"/>" rel="stylesheet" type="text/css"/>
<!-- jQuery -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js"/>"></script>
</head>
<body>
<div class="wrap col-lg-8 offset-lg-2">
	<header class="blogheader">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"/>
	</header>
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="<c:url value="basic"/>">기본설정</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="category"/>">카테고리</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="write"/>">글작성</a></li>
			</ul>
		</div>
	</nav>
	<div id="content">
	<div class="col-6 col-md-7">
		<form name="modifyForm" action="<c:url value="basic"/>" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="userNo" value="${ blogVo.userNo }">
			<div class="form-group">
				<label for="bolgTitle">블로그 제목</label>
				<input name="blogTitle" type="text" value="${ blogVo.blogTitle }" class="form-control">
			</div>
			<div class="form-group">
			<label>로고이미지</label><br/>
				<input type="hidden" name="logoFile" value="${ blogVo.logoFile }">
				<img src="<c:url value="/upload/${ blogVo.logoFile }"/>" id="logoPreview">
				<input type="file" name="uploadFile" class="form-control-file">
			</div>
			<input id="btnSubmit" type="submit" value="기본설정변경" class="btn btn-info">
		</form>
	</div>
	</div>
	<footer class="blogfooter">
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"/>
	</footer>
</div>
</body>
<script>
$("#btnSubmit").on("click", function(event){
	var blogTitle = document.modifyForm.blogTitle.value.trim();
	if(blogTitle.length == 0){
		alert("블로그 제목은 공백일 수 없습니다.");
		return false;
	} 
});
</script>
</html>