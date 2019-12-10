<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${ empty list }">
<p>검색 결과가 없습니다.</p>
</c:if>
<c:if test="${ not empty list }">
<p>검색 결과</p>
	<table class="table table-sm">
		<c:forEach items="${ list }" var="blogVo">
			<tr>
				<td><a href="<c:url value="/${ blogVo.id }"/>"><strong>${ blogVo.blogTitle }</strong></a></td>
				<td>by ${ blogVo.id }</td>
			</tr>
		</c:forEach>
	</table>
</c:if>