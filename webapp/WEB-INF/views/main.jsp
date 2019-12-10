<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBLOG - 펭수와 함께 하는 블로그</title>
<link rel="stylesheet" href="<c:url value="/assets/bootstrap/css/bootstrap.css"/>"/>
<link href="<c:url value="/assets/jblog/css/mainstyle.css"/>" rel="stylesheet" type="text/css"/>
<!-- jQuery -->
<script src="<c:url value="/assets/jquery/jquery-3.4.1.min.js"/>"></script>
</head>
<body style="overflow:hidden;">
<div id="maincontent">
	<header class="mainheader">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
	</header>
	<div class="col-lg-4 offset-lg-4 col-md-6 offset-md-3 text-center">
		<form name="searchform" action="<c:url value="/search"/>" method="POST" onsubmit="return false;">
			<div class="input-group">
				<input type="text" name="keyword" class="form-control" id="keywordinput">
				<div class="input-group-append">
					<button id="btnSearch" name="btnSearch" type="button" class="btn btn-info">검색</button>
				</div>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="keywordType" id="blogTitle" value="blogTitle" checked>
				<label class="form-check-label" for="blogTitle">블로그제목</label>
				<input class="form-check-input ml-2" type="radio" name="keywordType" value="id" id="userId">
				<label class="form-check-label" for="userId">블로거ID</label>
			</div>
		</form>
	</div>
	<div class="col-lg-4 offset-lg-4 col-md-6 offset-md-3" id="searchresult"></div>
</div>
</body>
<script>
$(document).ready(function() {
	$("#btnSearch").on("click", function(event){		
		var keyword = document.searchform.keyword.value.trim();
		var keywordType = document.searchform.keywordType.value;
		if(keyword.length < 2){
			alert("검색어는 최소 2글자 이상 입력해주세요");
			return false;
		}
		$.ajax({
		    url : "<c:url value="/search"/>",
		    dataType : "html",
		    type : "post",
		    data : {keyword: keyword, keywordType: keywordType},
		    success : function(result){
		        $("#searchresult").html(result);
		    }
		});
	});
	$("#keywordinput").keyup(function(event) {
		if(event.keyCode == '13') {
			$("#btnSearch").trigger("click");
		}
	});
});
</script>
</html>