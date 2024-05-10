<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<h1>得点管理システム</h1>
<h2>ログイン</h2>
</head>
<body>
<p>${errors.get("kome")}</p>
<form action="LoginExecute.action" method="post">
	<!--  ID -->
	<div class="id">
	<label>ID</label>
	<input type="text" name="id"  maxlength="20" value="teacher01" required>
	</div>

	<!-- パスワード -->
	<div class="pass">
	<label>パスワード</label>
	<input type="password" id=password name="password" value="pass01" required>
	</div>

	<!-- パスワード表示チェックボックス -->
	<div class="box">
	<input type="checkbox" id="showPassword" onchange="togglePasswordVisibility()" />
	<label for="showPassword">パスワードを表示する</label>

	<script>
		function togglePasswordVisibility() {
			let passwordInput = document.getElementById("password");
			let showPasswordCheckbox = document.getElementById("showPassword");

			if (showPasswordCheckbox.checked) {
				passwordInput.type = "text";
			} else {
				passwordInput.type = "password";
			}
		}
	</script>
	<a href="" class="btn btn-solid-gold"><input type="submit" name="login" value="ログイン"/></a>
</form>
</body>
</html>