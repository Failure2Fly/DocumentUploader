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

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">

<link rel="stylesheet" href="<c:url value="/CSS/global.css"/>">

<title>Password Recovery</title>
<script>
</script>
</head>
<body>
	<div class="header">
		<div class="mainHeaderRight"></div>
		<div class="mainTitle">
			<h1>Password Recovery</h1>
		</div>
		<div class="mainHeaderRight">
			<a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Back to Login</button>
			</a>
		</div>
	</div>
	<p>${sessionScope.recoveryError}</p>
	<div id="update-form" class="update">
		<sf:form method="POST" action="/DocumentUploader/recoveryPassword" id="temp-name">
			<legend>Enter Username:</legend>
			<fieldset>
				<input type="text" class="inputField" id="addInput"name="username"type="text" 
				placeholder="Enter username">
				<br>
				<br> 
				<input class="button" id="addButton" type="submit"
					value="Enter">
			</fieldset>
		</sf:form>
		<sf:form method="POST" action="/DocumentUploader/recoveryPassword/question" id="temp-name2">
			<legend>Enter Security Question Answer:</legend>
			<fieldset>
			<p>Security Question: ${securityQuestion}</p>
				<input type="text" class="inputField" id="addInput" name="answer"type="text" 
				placeholder="Enter Answer">
				<br>
				<br> <input class="button" id="addButton" type="submit"
					value="Enter">
			</fieldset>
		</sf:form>
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