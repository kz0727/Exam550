<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成績参照</title>
</head>
<body>
<h2>成績参照</h2>
	<form action = "TestListStudentExecute.action" method="post">
		<label>学生番号</label>
		<input type="text" name="student_no"
			placeholder="学生番号を入力してください" maxlength="10" required />
		<div>${errors.get("student_no")}</div>
		<input type="submit" value="検索">
	</form>

</body>
</html>