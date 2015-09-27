<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/styleAuthorization.css" type="text/css" rel="stylesheet">
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
				<li><p><a href="index.jsp">Home page</a></p></li>
				<li><p><a href="library">Library</a></p></li>
				<c:if test="${registered}">
					<li><p><a href="upload.jsp">Upload books</a></p></li>
					<li><p><a href="add_book">Add book</a></p></li>
				</c:if>
				<c:if test="${!registered}">
					<li><p><a href="#login">Upload books<a></a></p></li>
					<li><p><a href="#login">Add book<a></a></p></li>
					<div id="login" class="login">
						<div>
							<form method="post" action="login">
								<a href="#close" title="close" class="close">X</a>
								<h2>Welcome!</h2>
								<p>Please enter your login and password to continue</p>
								<div class="login_row">
									<label for="login">Login</label>
									<input type="text" name="login" id="login" maxlength="20" required />
								</div>
								<div class="login_row">
									<label for="password">Password</label>
									<input type="password" name="password" id="password" maxlength="30" required />
								</div>
								<p></p>
								<input type="submit" value="Log in" />
								<p><a href="authorization.jsp">Authorization</a></p>
							</form>
						</div>
					</div>
				</c:if>
				<c:if test="${registered}">
					<li><p><a href="logout">Log out</a></p></li>
				</c:if>
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
