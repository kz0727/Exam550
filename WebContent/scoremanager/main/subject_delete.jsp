<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>得点管理システム</title>
</head>
<body>
	<h2>科目情報削除</h2>
	<form action = "SubjectDeleteExecute.action" method="post">
		<input type="hidden" name="subject_cd" value="${subject_cd}">
		<input type="hidden" name="name" value="${name}">
		<p>「${name}（${subject_cd}）」を削除してもよろしいですか</p>

		<input type="submit" value="削除">

	</form>

	<a href="SubjectList.action">戻る</a>

</body>
</html>