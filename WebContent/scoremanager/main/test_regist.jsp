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
	<%--入学年度の入力 --%>>
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
<%--科目を入力　--%>>
<label>科目</label>
<select name="f3">
<option value="0">--------</option>
<c:forEach var="subject_cd" items="${slist}">

<option value="${subject_cd.subject_cd}" <c:if test="${subject_cd.subject_cd==f3}">selected</c:if>>${subject_cd.name}</option>
</c:forEach>
</select>
<%--回数の入力 --%>>
<label>回数</label>
<select name="f4">
<option value="0">--------</option>
<option value="1"<c:if test="${num==1}">selected</c:if>>1</option>
<option value="2"<c:if test="${num==2}">selected</c:if>>2</option>


</select>
<%--検索ボタンを設置 --%>>
		<button>検索</button>

		<div>${errors.get("f1")}</div>
</form>
<c:choose>
	<c:when test="${tests.size()>0}">
<h2>科目：${subject.name}（${f4}回）</h2>
<form  action="TestRegistExcecute.action">
<table class="table table-hover">
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>学生番号</th>
<th>氏名</th>
<th>点数</th>
</tr>
<%-- 取得したテスト結果の表示 --%>
<c:forEach var="test" items="${tests}">
<tr>
<td>${test.student.entYear}</td>
<td>${test.classNum}</td>
<td>${test.student.student_no}</td>
<td>${test.student.name}</td>
<td>
<c:choose>
<c:when test="${test.point=='0'}"><input type="text" name="point_${test.student.student_no }"value=""/></c:when>
<c:otherwise><input type="text" name="point_${test.student.student_no}"value="${test.point }"/></c:otherwise>
</c:choose>
<div>${errors.get("test_error")}</div>
</td>
</tr>

</c:forEach>
</table>
<input type="hidden" name="f1" value="${f1}"/>
<input type="hidden" name="f2" value="${f2}"/>
<input type="hidden" name="f3" value="${f3}"/>
<input type="hidden" name="f4" value="${f4}"/>
<input type="submit"  value="登録して終了">
</form>
</c:when>
	</c:choose>
</body>
</html>