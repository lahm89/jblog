<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ blogVo.blogTitle } - JBLOG</title>
<!-- jQuery -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<link href="<c:url value="/assets/jblog/css/mainstyle.css"/>" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="wrap col-lg-8 offset-lg-2">
	<header class="blogheader">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"/>
	</header>
	<div class="row">
		<div class="col" id="content">
			<div id="post">
				<div id="postContent">
					<c:if test="${ empty postVo }">
					<h4><strong>등록된 글이 없습니다.</strong></h4>
					</c:if>
					<c:if test="${ not empty postVo }">
					<h4><strong>${ postVo.postTitle }</strong></h4>
					<p>${ postVo.postContent }</p>
					</c:if>
				</div>
				<div id="comment"></div>
			</div>
			<div id="postList">
				<table class="table table-sm table-striped">
					<tr class="table-info">
						<th scope="col" colspan="2">${ categoryName } 카테고리 글 목록</th>
					</tr>
					<c:if test="${ empty postList }">
						<tr><td colspan="2">이 카테고리에는 등록된 글이 없습니다.</td></tr>
					</c:if>
					<c:forEach items="${ postList }" var="postVo">
					<tr>
						<td class="col">
						<a href="<c:url value="?cateNo=${ categoryNo }&postNo=${ postVo.postNo }"/>" id="posttitle" style="text-decoration:none">${ postVo.postTitle }</a>
						</td>
						<td class="col-2">
						<fmt:formatDate var="resultRegDate" value="${ postVo.regDate }" pattern="yyyy/MM/dd"/>${ resultRegDate }
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="col col-3" id="categoryCol">
			<img src="<c:url value="/upload/${ blogVo.logoFile }"/>" class="img-fluid"><br/>
			<div class="list-group">
				<a href="<c:url value="/${id}"/>" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center list-group-item-info">카테고리</a>
				<c:forEach items="${ cateList }" var="cateVo">
					<a href="<c:url value="?cateNo=${ cateVo.cateNo }"/>" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">${ cateVo.cateName }<span class="badge badge-info badge-pill">${ cateVo.postCount }</span></a>
				</c:forEach>
			</div>
		</div>
	</div>

	<footer class="blogfooter">
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"/>
	</footer>
</div>
</body>

<script>
$(document).ready(function() {
	var postNo = ${postVo.postNo}
	$.ajax({
	    url : "<c:url value="/getcomment"/>",
	    dataType : "html",
	    type : "post",
	    data : {postNo: postNo},
	    success : function(result){
	        $("#comment").html(result);
	    }
	});
});
</script>
</html>