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
<title>DocumentUploader Login</title>
</head>

<body>
	<h1>THIS IS THE LOGIN PAGE</h1>
	<div id="login-form" class="form login-form">
		<sf:form commandName="userAccount" method="POST" action="login">
			<fieldset class="login-form">
				<sf:input path="username" type="text" class="fas" id="fields"
					placeholder="Enter your username" />
				<br>
				<sf:input path="password" type="text" class="fas" id="fields"
					placeholder="Enter your password" />
				<br> 
				<input class="button" id="loginButton" type="submit" value="Login">


			</fieldset>
		</sf:form>
	</div>
</body>

<footer>
	<div class="row">
		<div class="col-4 social">
			<i class="fab fa-facebook-f"></i> <i class="fab fa-twitter"></i> <i
				class="fab fa-linkedin-in"></i> <i class="fab fa-google-plus-g"></i>
			<i class="fab fa-youtube"></i>

		</div>
		<div class="col-8 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
</footer>
</html>