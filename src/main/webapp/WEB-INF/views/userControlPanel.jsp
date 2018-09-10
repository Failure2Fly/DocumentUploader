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

<title>DocumentUploader User Account Panel</title>
</head>

<body>
	<div class="header"><div class = "registrationTitle"><h1>${sessionScope.user.username} Change User Account Information</h1></div></div>
	

	<h2>Change User Account Information</h2>

	<div id="register-form" class="registration">
		<sf:form  commandName="changedUser" method="POST"  action="userControlPanel">
			<fieldset>
				<p style="display:inline;">First Name: ${sessionScope.user.firstName}</p>
				<button class="hidden" onclick="myfuntion()">Change First Name</button> 
				<br>
				<sf:input path="firstName" type="text" name="FirstName" placeholder="new First name " value="" />
				<br><br>
				<p style="display:inline;">Last Name: ${sessionScope.user.lastName}</p> 
				<button class="hidden" onclick="">Change Last Name</button> 
				<br>
				<sf:input path="lastName" type="text" name="LastName" placeholder="Last name" value="" />
				<br><br>
				<p style="display:inline;">Email: ${sessionScope.user.userEmail}</p> 
				<button class="hidden" onclick="">Change Email</button> 
				<br>
				<sf:input path="userEmail" type="text" name="Email" placeholder="Email" />
				<br><br>
				<p style="display:inline;">Username: ${sessionScope.user.username}</p>
				<button class="hidden" onclick="">Change Username</button>  
				<br>
				<sf:input path="username" type="text" name="UserName" placeholder="Create a username" />
				<br><br>
				<p style="display:inline;">Password: ********</p>
				<button class="hidden" onclick="">Change Password</button>  
				<br>
				<sf:input path="password" type="password" name="Password" placeholder="Create a password" />
				<br>
				<br> 
				<input class="button" id="registerButton" type="submit" value="Change User Account Information"/>
			</fieldset>
		</sf:form>
	</div>
<script src="./JS/global.js"></script>
</body>
<footer>
<div class="header">
	<div class="row">
		<div class="col-4 social">
			<a class="fab fa-facebook-f" href="https://www.facebook.com"></a> 
			<a class="fab fa-twitter" href="https://www.twitter.com"></a> 
			<a class="fab fa-linkedin-in" href="https://www.linkedin.com"></a> 
			<a class="fab fa-google-plus-g" href="http://www.plus.google.com"></a>
			<a class="fab fa-youtube" href="http://www.youtube.com"></a>
		</div>
		<div class="col-8 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
</div>
</footer>
</html>
