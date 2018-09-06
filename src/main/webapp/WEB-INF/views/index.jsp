<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<%-- integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> --%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<%-- integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"> --%>
<link rel="stylesheet" href="<c:url value="./CSS/global.css"/>">
<title>DocumentUploader Home</title>
</head>

<body>
	<div class="header">
		<div class="registrationTitle"><h1>Ye Olde Renaissance Document Uploader</h1></div>
		<div class="nav-buttons">
			<a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Login</button>
			</a> 
			<a href="/DocumentUploader/register">
				<button	class="btn btn-primary" type="submit">Register</button>
			</a>
		</div>
	</div>
	<nav class="navbar navbar-expand-lg">
		<div class="container logo">
			<a class="navbar-brand" href="#"><img src="<c:url value=""/>" /></a>
		</div>
		<div class="nav-right">
			<div class="nav phone">
				<i class="fas fa-phone"></i>
				<p>Call DU 800 800 8000</p>
			</div>

		</div>
	</nav>

	<div>
		<h1>This is the home page</h1>
	</div>
	<div class="main-img" style="background-image: url(./imgs/main.jpg)">
	</div>





</body>

<footer>
<div class="header">
	<div class="row">
		<div class="col-4 social">
			<a class="fab fa-facebook-f" href="https://www.facebook.com"></a> <a
				class="fab fa-twitter" href="https://www.twitter.com"></a> <a
				class="fab fa-linkedin-in" href="https://www.linkedin.com"></a> <a
				class="fab fa-google-plus-g" href="http://www.plus.google.com"></a>
			<a class="fab fa-youtube" href="http://www.youtube.com"></a>

		</div>
		<div class="col-8 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
	</div>
</footer>

</html>