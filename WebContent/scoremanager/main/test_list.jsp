<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

<c:param name="content">
<h2>成績参照</h2>

<!-- 科目別成績検索 -->
<div class="border border-3 rounded" style=" height: 220px;">

	<form action="TestListSubjectExecute.action" method="post">
	<div class="mt-3 ms-3" >科目情報</div>

		<div class="mt-1 ms-3" >
		<label>入学年度</label>
		<select name="f1">
			<option value="0">-------</option>

			<%-- c:forEachは繰り返し --%>
			<c:forEach var="year" items="${ent_year_set}">
				<%-- 現在のyearと選択されたf1が一致していた場合selectedを追記 --%>
				<option value="${year}" <c:if test="${year==f1}">selected</c:if>> ${year}</option>
			</c:forEach>
		</select>

		<label>クラス</label>
		<select name="f2">
			<option value="0">-------</option>
			<%-- c:forEachは繰り返し --%>
			<c:forEach var="num" items="${class_list}">
				<%-- 現在のクラス番号と選択されたf2が一致していた場合selectedを追記 --%>
				<option value="${num}" <c:if test="${num==f2}">selected</c:if>> ${num}</option>
			</c:forEach>
		</select>

		<label>科目</label>
		<select name="f3">
			<option value="0">-------</option>
			<%-- c:forEachは繰り返し --%>
			<c:forEach var="subject" items="${subject_list}">
				<%-- 現在の科目と選択されたf3が一致していた場合selectedを追記 --%>
				<option value="${subject.subject_cd}" <c:if test="${subject.name==f3}">selected</c:if>> ${subject.name}</option>
			</c:forEach>
		</select>
	<input type="submit" value="検索">


	</div>
	</form>
	<!-- エラーがあった場合表示 -->
	<div class="ms-3">${errors.get("select")}</div>

<!-- 検索のセンターライン -->
<hr class= "ms-4" style="width: 20cm;">

<!-- 学生別成績検索 -->
<div class="mt-2 mb-3 ms-3" >
	<form action = "TestListStudentExecute.action" method="post">
	<div class=" mb-1" >学生情報</div>
		<label>学生番号</label>
		<input type="text" name="student_no"
			<%-- もし前回も学生番号を入力していたらその値を返す --%>
			placeholder="学生番号を入力してください" maxlength="10" value="${f4}" required />
		<input type="submit" value="検索">
	</form>

</div>

</div>

</c:param>

</c:import>