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
				<li class="nav-item"><a class="nav-link" href="<c:url value="basic"/>">기본설정</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="category"/>">카테고리</a></li>
				<li class="nav-item active"><a class="nav-link" href="<c:url value="write"/>">글작성</a></li>
			</ul>
		</div>
	</nav>
	<div id="content">
	<div class="col-7 col-md-9">
		<form name="postForm" action="<c:url value="write"/>" method="POST">
		<div class="form-group row">
			<label for="postTitle" class="col-auto col-form-label">제목</label>
			<div class="col pr-0">
				<input name="postTitle" type="text" class="form-control mb-2">
			</div>
			<div class="col-auto">
				<select name="cateNo" class="custom-select">
					<c:forEach items="${ cateList }" var="cateVo">
						<option value="${ cateVo.cateNo }">${ cateVo.cateName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group row">
			<label for="postTitle" class="col-auto col-form-label">내용</label>
			<div class="col">
				<textarea name="postContent" class="form-control mb-2" rows=10></textarea>
			</div>
		</div>
			<input id="btnSubmit" type="submit" value="포스트 하기" class="btn btn-info">
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
	var postTitle = document.postForm.postTitle.value.trim();
	if(postTitle.length == 0){
		alert("포스트 제목은 공백일 수 없습니다.");
		return false;
	} 
});
</script>
</html>