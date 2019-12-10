<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${ not empty authUser }">
	<form id="commentForm" name="commentform" action="<c:url value="/writecomment"/>" method="POST">
		<input type="hidden" name="userNo" value="${ authUser.userNo }">
		<input type="hidden" name="postNo" value="${ postNo }">
		<div class="col-12">
		<table class="table table-sm table-borderless">
			<tr>
				<td style="width:10%" class="align-middle">${ authUser.userName }</td>
				<td><input class="form-control" type="text" name="cmtContent"></td>
				<td style="width:10%"><input id="btnSubmit" type="submit" value="댓글달기" class="btn btn-info"></td>
			</tr>
		</table>
		</div>		
	</form>
</c:if>
<c:if test="${ not empty commentList }">
	<table class="table" id="commentlist">
		<c:forEach items="${ commentList }" var="cmtVo">
			<tr>
				<td style="width:15%" class="align-middle"><strong>${ cmtVo.userName }</strong></td>
				<td>${ cmtVo.cmtContent }</td>
				<td style="width:10%"><fmt:formatDate var="resultRegDate" value="${ cmtVo.regDate }" pattern="yyyy/MM/dd"/>${ resultRegDate }</td>
				<td style="width:2%">
				<c:if test="${ authUser.userNo == cmtVo.userNo }">
					<a href="<c:url value="/deletecomment/${ cmtVo.cmtNo }"/>"><span class="badge badge-pill badge-info">X</span></a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<script>
$("#btnSubmit").on("click", function(event){
	var cmtContent = document.commentform.cmtContent.value.trim();
	console.log("commentForm.cmtContent:", cmtContent);
	if(cmtContent.length == 0){
		alert("댓글 내용을 입력해주세요.");
		return false;
	} 
});
</script>


