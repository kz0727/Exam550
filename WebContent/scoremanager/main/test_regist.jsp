<%-- 学生一覧JSP --%>
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

	<h2>成績管理</h2>


	<form method="get">
<label>入学年度 </label>
<select name="f1">
<option value="0">--------</option>
<%-- c:forEachは繰り返し --%>
<c:forEach var="year" items="${ent_year_set}">
<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>

		<label>クラス</label>
<select name="f2">
<option value="0">--------</option>
<c:forEach var="num" items="${class_num_set}">
<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
</c:forEach>
</select>
<label>科目</label>
<select name="f3">
<option value="0">--------</option>
<c:forEach var="subject_cd" items="${slist}">

<option value="${subject_cd.subject_cd}" <c:if test="${subject_cd.subject_cd==f3}">selected</c:if>>${subject_cd.name}</option>
</c:forEach>
</select>
<label>回数</label>
<select name="f4">
<option value="0">--------</option>
<c:forEach var="num" items="${num}">
<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
</c:forEach>
</select>

		<button>検索</button>

		<div>${errors.get("f1")}</div>
</form>
</body>
</html>