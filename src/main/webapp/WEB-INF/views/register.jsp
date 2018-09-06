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

<title>DocumentUploader Register</title>
</head>

<body>
	<h1>This is the Register Page</h1>

	<h2>Register</h2>

	<div id="register-form" class="form register-form">
		<sf:form commandName="userAccount" method="POST" action="register">
			<fieldset class="register-form">
				<sf:input path="firstName" type="text" class="fas" id="fields"
					name="FirstName" placeholder="First name " value="" />
				<br>
				<sf:input path="lastName" type="text" class="fas" id="fields"
					name="LastName" placeholder="Last name" value="" />
				<br>
				<sf:input path="userEmail" type="text" class="fas" id="fields"
					 name="Email" placeholder="Email" />
				<br>
				<sf:input path="username" type="text" class="fas" id="fields"
					name="UserName" placeholder="Create a username" />
				<br>

				<sf:input path="password" type="password" class="fas" id="fields"
					name="Password" placeholder="Create a password" />
				<br> <br> <select>
				<option value="pet">What is the name of your first pet?</option>
					<option value="maiden">What is the maiden name of your
						mother?</option>
					<option value="concert">What was the first concert you
						attended?</option>
					<option value="car">What was the make and model of your
						first car?</option>
					<option value="artist">Who is your favorite actor, musician, or artist?</option>
				</select> 
				<br> 
				<input type="text" class="fas" id="fields"
					name="securityQuestion" placeholder="Security Answer " value="" />
				<br> 
				<input type="checkbox" name="agree" value="agree">
				I accept <span>UD's terms and conditions</span> 
				<br> 
				<input class="button" id="registerButton" type="submit" value="Register">


			</fieldset>
		</sf:form>
	</div>

</body>

<footer>
	<div class="row">
		<div class="col-4 social">
			<a class="fab fa-facebook-f" href="https://www.facebook.com"></a> <a class="fab fa-twitter" href="https://www.twitter.com"></a> <a
				class="fab fa-linkedin-in" href="https://www.linkedin.com"></a> <a class="fab fa-google-plus-g" href="http://www.plus.google.com"></a>
			<a class="fab fa-youtube" href="http://www.youtube.com"></a>

		</div>
		<div class="col-8 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
</footer>
</html>
