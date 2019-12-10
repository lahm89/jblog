<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입 - JBLOG</title>
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
	<div class="col-md-4 offset-md-4 col-lg-2 offset-lg-5">
		<form id="join-form" name="registerForm" action="<c:url value="/users/join"/>" method="POST">
			<div class="form-group">
				<label for="userName">이름</label>
				<input class="form-control" name="userName" type="text"/>
			</div>
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="input-group">
					<input class="form-control" type="text" name="id" id="idInput" checkResult="false"/>
					<div class="input-group-append">
					<button id="btnCheckId" type="button" class="btn btn-outline-info">중복 체크</button>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label>
				<input class="form-control" name="password" type="password"/>
			</div>
			<div class="form-group">
				<label for="agreement">약관동의</label><br/>
				<input type="checkbox" name="agreement" value=""/>서비스 약관에 동의합니다.
			</div>
			<input id="btnSubmit" type="submit" class="btn btn-info btn-block" value="회원가입">	
		</form>
	</div>
</div>
</body>
<script>
$(document).ready(function() {
	// 아이디 중복 체크
	$("#btnCheckId").on("click", function(event) {
		var id = document.registerForm.id.value.trim();
		if(id.length == 0){
			alert("아이디를 입력해 주세요.")
			return;
		}
		// Ajax Call
		$.ajax({
			url: "<c:url value="/users/checkId"/>",
			type: "GET",
			data: {id: id},
			dataType: "json",
			success: function(result) {
				console.log("Result:", result);
				if(result.exists) {
					alert("다른 아이디로 가입해 주세요.");
				} else {
					$('#idInput').attr("checkResult", "true");
					alert("사용할 수 있는 아이디 입니다.");
				}
			},
			error: function(request, status, error) {
				console.error("Error:", error);
			}
		});
	});
	// 아이디 입력 변경 시 중복 체크여부 false로 돌리기
	$("#idInput").change(function () {
	      $('#idInput').attr("checkResult", "false");
	});
	// 가입 폼 검증
	$("#btnSubmit").on("click", function(event){
		var chk=document.registerForm.agreement.checked;
		var id = document.registerForm.id.value.trim();
		var name = document.registerForm.userName.value.trim();
		var password = document.registerForm.password.value.trim();
		if(name.length == 0){
			alert("이름을 입력해주세요");
			return false;
		} else if(id.length == 0){
			alert("아이디를 입력해주세요");
			return false;
		} else if($("#idInput").attr("checkResult") == "false"){
			alert("아이디 중복체크를 해주세요");
			return false;
		} else if(password.length == 0){
			alert("패스워드를 입력해주세요");
			return false;
		} else if(!chk){
			alert("약관에 동의해 주세요");
			return false;
		}
	});
});

</script>
</html>