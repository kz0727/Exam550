<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../../common/base.jsp">
	<c:param name="title">得点管理システム</c:param>

<c:param name="content">
<h2>成績一覧(${titlesub}${titlestu})</h2>

<!-- 科目別成績検索 -->
<div class="border border-3 rounded" style=" height: 200px;">

	<form action="TestListSubjectExecute.action" method="post">
	<div class="mt-3 ms-3" >科目情報</div>

		<div class="mt-3 ms-3" >
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
				<option value="${subject.subject_cd}" <c:if test="${subject.subject_cd==f3}">selected</c:if>> ${subject.name}</option>
			</c:forEach>
		</select>
	<input type="submit" value="検索">


	</div>
	</form>

<!-- 検索のセンターライン -->
<hr class= "ms-4" style="width: 20cm;">

<!-- 学生別成績検索 -->
<div class="mt-4 mb-3 ms-3" >
	<form action = "TestListStudentExecute.action" method="post">
		<label>学生番号</label>
		<input type="text" name="student_no"
		<%-- もし前回も学生番号を入力していたらその値を返す --%>
			placeholder="学生番号を入力してください" maxlength="10" value="${f4}" required/>
		<input type="submit" value="検索">
	</form>

</div>

</div>

	<c:choose>

		<!-- 科目別成績一覧があった場合表示する -->
		<c:when test="${list.size()>0}">
		<div>氏名:${student.name}(${student.student_no})</div>
			<div>検索結果：${list.size()}件</div>

			<table class="table table-sm">
				<tr>
					<th>科目名</th>
					<th>科目コード</th>
					<th>回数</th>
					<th>点数</th>
				</tr>
				<c:forEach var="list" items="${list}">
					<tr>
    					<td>${list.subjectName}</td>
   						<td>${list.subjectCd}</td>
    					<td>${list.point}</td>
    					<td>${list.num}</td>
					</tr>

				</c:forEach>
		</table>
		</c:when>

		<!-- 学生別成績一覧が存在した場合表示 -->
		<c:when test="${list.size()== 0}">
			<div>氏名:${student.name}(${student.student_no})</div>
			<div>成績情報が存在しませんでした</div>
		</c:when>

		<c:when test="${tsublist.size()>0}">
			<div>科目:${subject.name}</div>

			<table class="table table-striped">
				<tr>
					<th scope="col">入学年度</th>
					<th scope="col">クラス</th>
					<th scope="col">学生番号</th>
					<th scope="col">氏名</th>
					<th class ="text-center">1回</th>
					<th class ="text-center">2回</th>
				</tr>
				<c:forEach var="tsublist" items="${tsublist}">
					<tr>
    					<td>${tsublist.entYear}</td>
   						<td>${tsublist.classNum}</td>
    					<td>${tsublist.studentNo}</td>
    					<td>${tsublist.studentName}</td>
    					<td>${tsublist.getPoint(1)}</td>
    					<td>${tsublist.getPoint(2)}</td>
					</tr>

				</c:forEach>
		</table>
		</c:when>

		<!-- エラーが存在した場合表示 -->
		<c:when test="${tsublist.size()== 0}">
			<div>学生情報が存在しませんでした</div>
		</c:when>

		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>

	</c:choose>

</c:param>

</c:import>