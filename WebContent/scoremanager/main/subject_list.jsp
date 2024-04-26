<%-- 科目一覧JSP --%>
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

	<h2>科目管理</h2>
	<a href="StudentCreate.action">新規登録</a>



	<table class="table table-hover">
		<tr>
			<th>科目コード</th>
			<th>科目名</th>

		</tr>
		<c:forEach var="subject" items="${subjects}">
			<tr>
				<td>{subject.subject_cd}</td>
				<td>{subject.name}</td>
				<td>変更</td>
				<td>削除</td>
			</tr>
		</c:forEach>
	</table>
<c:otherwise>
	<div>学生情報が存在しませんでした</div>
</c:otherwise>

</body>
</html>