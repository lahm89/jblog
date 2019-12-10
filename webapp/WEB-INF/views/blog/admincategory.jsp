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
				<li class="nav-item active"><a class="nav-link" href="<c:url value="category"/>">카테고리</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="write"/>">글작성</a></li>
			</ul>
		</div>
	</nav>
	<div id="content">
	<div class="col-7 col-md-9">
		<table class="table table-bordered table-sm">
			<thead class="thead-dark">
				<tr>
					<th>번호</th>
					<th>카테고리명</th>
					<th>포스트수</th>
					<th>설명</th>
					<th>삭제</th>
				</tr>
			</thead>
			<!-- Loop -->
			<c:forEach items="${ cateList }" var="cateVo">
			<tr>
				<td>${ cateVo.cateNo }</td>
				<td>${ cateVo.cateName }</td>
				<td>${ cateVo.postCount }</td>
				<td>${ cateVo.description }</td>
				<td><a href="<c:url value="category/${ cateVo.cateNo }"/>" class="deleteCategory"><span class="badge badge-pill badge-info">X</span></a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<div class="col-6 col-md-7" id="categoryform">
	<label for="categoryForm"><strong>새로운 카테고리 추가</strong></label>
	<form name="categoryForm" action="<c:url value="category"/>" method="POST">
		<div class="form-group row">
			<label for="cateName" class="col-3 col-form-label">카테고리명</label>
			<div class="col">
				<input name="cateName" class="form-control form-control-sm mb-2" type="text">
			</div>
		</div>
		<div class="form-group row">
			<label for="description" class="col-3 col-form-label">설명</label>
			<div class="col">
				<input name="description" class="form-control form-control-sm mb-2" type="text">
			</div>
		</div>
		<input id="btnSubmit" type="submit" value="카테고리 추가" class="btn btn-info">
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
	var cateName = document.categoryForm.cateName.value.trim();
	if(cateName.length == 0){
		alert("카테고리명은 공백일 수 없습니다.");
		return false;
	} 
});
$(".deleteCategory").on("click", function(event){
	var tr = $(this).parent().parent();
	var td = tr.children();
	var postCount = td.eq(2).text();
	var size = ${ cateList.size() };
	if(size == 1){
		alert("카테고리는 최소 1개가 있어야 합니다.");
		return false;
	}
	if(postCount != 0){
		alert("포스트가 있는 카테고리는 삭제할 수 없습니다.");
		return false;
	}
});
</script>
</html>