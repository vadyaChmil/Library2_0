<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>DownloadBooks</title>
</head>
<body>
	<div id=wrapper>
		<div id="header">
			<a href="library"><img src="images/OpenBooks.png" width="116" height="80" id="OpenBooks"></a>
			<img src="images/HeadText.png" width="216" height="80" id="HeadText">
		</div>
		<div id="menu">
			<ul>
				<li><p><a href="index.jsp">Home page</a></p></li>
				<li><p><a href="library">Library</a></p></li>
				<c:if test="${registered}">
					<li><p><a href="logout">Log out</a></p></li>
				</c:if>
			</ul>
		</div>
		<div id="content">
			<p>Dear friend,</p>
			<p>You can upload your text file into this library. But You must
				be careful as you can.</p>
			<p>Cause is the content of your file must be as like as in next
				view</p>
			<img src="images/list01.png" width="368" height="77" title="file">
			<p>or</p>
			<img src="images/list02.png" width="368" height="77" title="file">
			<p>or</p>
			<img src="images/list03.png" width="368" height="77" title="file">
			<p></p>
			<form name="forma" method="post" enctype="multipart/form-data" action="add_books">
				<input name="file" type="File">
				<input name="submit" type="Submit" value="Send">
				<input name="reset" type="Reset" value="Reset">
			</form>
		</div>
		<div id="footer">
			<a href="https://vadya-zakusylo.rhcloud.com/"><img
				src="images/resource.png" width="190" height="20"></a>
		</div>
	</div>
</body>
</html>
