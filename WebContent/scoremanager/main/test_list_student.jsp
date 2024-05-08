<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>学生別成績一覧表</h2>

	<c:choose>
		<c:when test="${list.size()>0}">
			<div>検索結果：${list.size()}件</div>

			<table class="table table-hover">
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
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>

</body>
</html>