<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.5.min.js" type="text/javascript"></script>
<script src="js/equalHeight.js" type="text/javascript"></script>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/styleAuthorization.css" type="text/css" rel="stylesheet">
<title>Authorization</title>
</head>
<body>
	<div id=wrapper>
		<div id="header">
			<a href="library"><img src="images/OpenBooks.png" width="116"
				height="80" id="OpenBooks"></a> <img src="images/HeadText.png"
				width="216" height="80" id="HeadText">
		</div>
		<div id="menu">
			<ul>
				<li><p><a href="index.jsp">Home page</a></p></li>
				<li><p><a href="library">Library</a></p></li>
			</ul>
		</div>
		<div id="content">
			<div class="authorization">
				<form method="post" action="authorization">
					<h3>Please enter your data for authorization</h3>
					<p><input type="text" name="login" id="login" size="25%" maxlength="20" placeholder="Login" required="required"></p>
					<c:choose>
						<c:when test="${not empty user}">
							<p><input type="text" name="name" id="name" size="25%" maxlength="20" value="${user.name}" required="required"></p>
							<p><input type="text" name="surname" id="surname" size="25%" maxlength="20" value="${user.surname}" required="required"></p>
							<p><input type="email" name="email" id="email" size="25%" maxlength="40" value="${user.email}" required="required"></p>
						</c:when>
						<c:otherwise>
							<p><input type="text" name="name" id="name" size="25%" maxlength="20" placeholder="Name" required="required"></p>
							<p><input type="text" name="surname" id="surname" size="25%" maxlength="20" placeholder="Surname" required="required"></p>
							<p><input type="email" name="email" id="email" size="25%" maxlength="40" placeholder="E-mail adress" required="required"></p>
						</c:otherwise>
					</c:choose>					
					<p><input type="password" name="password" id="password" size="25%" maxlength="30" placeholder="Password" required="required"></p>
					<p><input type="reset" value="Reset"> <input type="submit" value="Send"></p>
					<c:if test='${not empty message}'>
						<p>${message}</p>
					</c:if>
				</form>
			</div>	
		</div>
		<div id="footer">
			<a href="https://vadya-zakusylo.rhcloud.com/"><img src="images/resource.png" width="190" height="20"></a>
		</div>
	</div>
</body>
</html>
