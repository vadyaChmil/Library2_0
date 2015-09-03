<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Insert books</title>
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
				<li><p><a href="upload.html">Upload books</a></p></li>
				<li><p><a href="add_book">Add book</a></p></li>
			</ul>
		</div>
		<div id="content">
			<p>${message}</p>
		</div>
		<div id="footer">
			<a href="https://vadya-zakusylo.rhcloud.com/"><img
				src="images/resource.png" width="190" height="20"></a>
		</div>
	</div>
</body>
</html>
