<%@ page isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/style.css" type="text/css" rel="stylesheet">
<title>Error page</title>
</head>
<body>
	<div id="error">
		<h1 class="error">ERROR</h1>
		<p>${errorMessage}</p>
		<p>You can back to <a href="index.html">Home page</a></p>
	</div>
</body>