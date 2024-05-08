<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<h2>得点管理システム</h2>
<p>ログイン</p>
</head>
<body>
<p>${errors.get("kome")}</p>
<form action="LoginExecute.action" method="post">
	<!--  ID -->
	<label>ID</label>
	<input type="text" name="id"  maxlength="20" value="teacher01" required>

	<!-- パスワード -->
	<label>パスワード</label>
	<input type="password" id=password name="password" value="pass01" required>

	<!-- パスワード表示チェックボックス -->
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
	<input type="submit" name="login" value="ログイン"/>

</form>
</body>
</html>