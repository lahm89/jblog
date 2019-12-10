<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2 class="text-center"><a href="<c:url value="/${ id }"/>" id="blogTitleLink" style="text-decoration:none"><strong>${ blogVo.blogTitle }</strong></a></h2>
<ul class="nav justify-content-end" id="loginmenu">
<c:choose>
		<c:when test="${ authUser.userNo == blogVo.userNo }">
			<!-- 블로그 주인일 경우 -->
			<li class="nav-item"><a class="nav-link" href="<c:url value="/${ authUser.id }/admin/basic"/>">내블로그 관리</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/users/logout"/>">로그아웃</a></li>
		</c:when>
		<c:when test="${ empty authUser }">
			<!-- 로그인 안된 경우 -->
			<li class="nav-item"><a class="nav-link" href="<c:url value="/users/login"/>">로그인</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/users/join"/>">회원가입</a></li>
		</c:when>
		<c:otherwise>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/${ authUser.id }"/>">내블로그</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/users/logout"/>">로그아웃</a></li>
		</c:otherwise>
</c:choose>
</ul>