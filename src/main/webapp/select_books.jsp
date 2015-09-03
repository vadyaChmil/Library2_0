<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Select books</title>
</head>
<body>
	<div id=wrapper>
		<div id="header">
			<a href="library"><img src="images/OpenBooks.png" width="116" height="80" id="OpenBooks"></a>
			<img src="images/HeadText.png" width="216" height="80" id="HeadText">
		</div>
		<div id="menu">
			<ul>
				<li><p><a href="index.html">Home page</a></p></li>
				<li><p><a href="library">Library</a></p></li>
			</ul>
		</div>
		<div id="content">
			<form method="post" action="insert_books">
			<table>
				<c:if test="${fn:length(uploadBookList) gt 0}">
					<tr>
						<th> Book code </th>
						<th> Author </th>
						<th> Title </th>
						<th> Year edition </th>
						<th> Pages </th>
					</tr>
					<c:forEach items="${uploadBookList}" var="book">
						<tr>
							<td class="left">
								<label><input type="Checkbox" name="bookCode" value="${book.code}" checked>
								<c:out value="${book.code}" />
								</label>
							</td>
							<td class="left"><c:out value="${book.autor}" /></td>
							<td class="left"><c:out value="${book.title}" /></td>
							<td><c:out value="${book.yearEdition}" /></td>
							<td><c:out value="${book.pages}" /></td>
						</tr>
						<tr>
							<td colspan="5" class="left" id="genre">
								<c:forEach items="${genreList}" var="genre">
									<label><input type="checkbox" name="${book.code}" value="${genre.id}">
										<c:out value="${genre.name}" /></label>
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td colspan="5" class="left">
						<c:if test="${fn:length(uploadBookList) gt 0}">
		   					<p>Uncheck book if don't want to upload it to library</p>
		   					<p><input type="Submit" name="submit" value="Send"></p>
						</c:if>
						<c:if test="${fn:length(uploadBookList) lt 1}">
					   		<p>Document is empty or you haven't chosen the document.</p>
						</c:if>
					<td>
				</tr>
			</table>
			</form>
		</div>
		<div id="footer">
			<a href="https://vadya-zakusylo.rhcloud.com/"><img
				src="images/resource.png" width="190" height="20"></a>
		</div>
	</div>
</body>
</html>
