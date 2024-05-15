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

	<form action="TestListSubjectExecute.action" method="post">
		<label>入学年度</label>
		<select name="f1">
			<option value="0">-------</option>

			<c:forEach var="year" items="${ent_year_set}">
				<option value="${year}" <c:if test="${year==f1}">selected</c:if>> ${year}</option>
			</c:forEach>
		</select>

		<label>クラス</label>
		<select name="f2">
			<option value="0">-------</option>
			<%-- c:forEachは繰り返し --%>
			<c:forEach var="num" items="${class_list}">
				<%-- 現在のyearと選択されたf1が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>> ${num}</option>
			</c:forEach>
		</select>

		<label>科目</label>
		<select name="f3">
			<option value="0">-------</option>
			<%-- c:forEachは繰り返し --%>
			<c:forEach var="subject" items="${subject_list}">
				<%-- 現在のyearと選択されたf1が一致していた場合selectedを追記 --%>
				<option value="${subject.subject_cd}" <c:if test="${subject.name==f3}">selected</c:if>> ${subject.name}</option>
			</c:forEach>
		</select>
	<input type="submit" value="検索">

	</form>

	<form action = "TestListStudentExecute.action" method="post">
		<label>学生番号</label>
		<input type="text" name="student_no"
			placeholder="学生番号を入力してください" maxlength="10" required />
		<input type="submit" value="検索">
	</form>

</body>
</html>