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
		<sf:form  commandName="userAccount" method="POST"  action="changeUserInfo">
			<fieldset>
					<p id="info" style="display:inline;">First Name: ${userAccount.getFirstName()}</p> 
				<button style="display:inline-block;" id="registerButton" type="button" onclick="cancelDisplayProperty()">change first name</button>
				<sf:input path="firstName" class="validate-form" type="text" name="FirstName" placeholder="new First name " value="" />
				<input class="button validate-form" id="registrationButton" type="submit" value="changed"/>
				<br>
					<p id="info" style="display:inline;">Last Name: ${userAccount.getLastName()}</p> 
				<button style="display:inline-block;" id="registerButton" type="button" onclick="cancelDisplayProperty()">change last name</button>
				<sf:input path="lastName" class="validate-form" type="text" name="LastName" placeholder="Last name" value="" />
				<input class="button validate-form" id="registrationButton" type="submit" value="changed"/>
				<br>
					<p id="info" style="display:inline;">Email: ${userAccount.getUserEmail()}</p> 
					<button style="display:inline-block;" id="registerButton" type="button" onclick="cancelDisplayProperty()">change email</button>
				<sf:input path="userEmail" class="validate-form" type="text" name="Email" placeholder="Email" />
				<input class="button validate-form" id="registrationButton" type="submit" value="changed"/>
				<br>
					<p id="info" style="display:inline;">Username: ${userAccount.getUsername()}</p> 
					<button style="display:inline-block;" id="registerButton" type="button"onclick="cancelDisplayProperty()">change username</button>
				<sf:input path="username" class="validate-form" type="text" name="UserName" placeholder="Create a username" />
				<input class="button validate-form" id="registrationButton" type="submit" value="changed"/>
				<br>
					<p id="info" style="display:inline;">Password: ${userAccount.getPassword()}</p> 
					<button style="display:inline-block;" id="registerButton" type="button" onclick="cancelDisplayProperty()">change password</button>
				<sf:input path="password" class="validate-form" type="password" name="Password" placeholder="Create a password" />
				<input class="button validate-form" id="registrationButton" type="submit" value="changed"/>
				<br>
				<br> 
				<input class="button" id="registerButton" type="submit" value="Change User Account Information"/>
			</fieldset>
		</sf:form>
	</div>
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
