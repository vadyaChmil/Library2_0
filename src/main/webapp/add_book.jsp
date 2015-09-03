<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Add Book</title>
</head>
<body>
	<div id=wrapper>
		<div id="header">
			<a href="library"><img src="images/OpenBooks.png" width="116" height="80" id="OpenBooks"></a>
			<img src="images/HeadText.png"width="216" height="80" id="HeadText">
		</div>
		<div id="menu">
			<ul>
				<li><p><a href="index.html">Home page</a></p></li>
				<li><p><a href="library">Library</a></p></li>
			</ul>
		</div>
		<div id="content">
			<p>Input code, author, title, year edition and pages of book and
				choose one or more genre for this book</p>
			<form method="post" action="insert_book">
				<table>
					<tr>
						<th><label for="code">Book code</label></th>
						<th><label for="author">Author</label></th>
						<th><label for="title">Title</label></th>
						<th><label for="year_edition">Year edition</label></th>
						<th><label for="pages">Pages</label></th>
					</tr>
					<tr>
						<td><input type="text" name="code" id="code" size="10%" maxlength="5" 
							onkeypress='return event.charCode >= 48 && event.charCode <= 57'
							required></td>
						<td><input type="text" name="author" id="author" size="25%" maxlength="25"
							required></td>
						<td><input type="text" name="title" id="title" size="45%" maxlength="40"
							required></td>
						<td><input type="text" name="year_edition" id="year_edition"
							size="10%" maxlength="4"
							onkeypress='return event.charCode >= 48 && event.charCode <= 57'
							required></td>
						<td><input type="text" name="pages" id="pages" size="10%" maxlength="4"
							onkeypress='return event.charCode >= 48 && event.charCode <= 57'
							required></td>
					</tr>
				</table>
				<p id="genre">
					<c:forEach items="${genreList}" var="genre">
						<label><input type="checkbox" name="genre" value="${genre.id}">
							<c:out value="${genre.name}" /></label>
					</c:forEach>
				</p>
				<p>
					<input type="reset" value="Reset"> <input type="submit"
						value="Send">
				</p>
			</form>
		</div>
		<div id="footer">
			<a href="https://vadya-zakusylo.rhcloud.com/"><img
				src="images/resource.png" width="190" height="20"></a>
		</div>
	</div>
</body>
</html>
