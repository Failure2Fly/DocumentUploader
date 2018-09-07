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
	<div class="header"><div class = "registrationTitle"><h1>This is the Register Page</h1></div></div>
	

	<h2>Register</h2>
	<div id="register-form" class="registration">
		<sf:form commandName="userAccount" method="POST" action="register">
			<fieldset>
					<p>First Name:</p>
				<sf:input path="firstName" class="inputField" type="text" 
					name="FirstName" placeholder="First name " value="" />
				<br>
					<p>Last Name:</p> 
				<sf:input path="lastName" class="inputField" type="text" 
					name="LastName" placeholder="Last name" value="" />
				<br>
					<p>Email:</p> 
				<sf:input path="userEmail" class="inputField" type="text" 
					 name="Email" placeholder="Email" />
				<br>
					<p>Username:</p> 
				<sf:input path="username" class="inputField" type="text" 
					name="UserName" placeholder="Create a username" />
				<br>
					<p>Password:</p> 
				<sf:input path="password" class="inputField" type="password" 
					name="Password" placeholder="Create a password" />
				<c:forEach var="listQA" items="listQA" varStatus="i">
				<br> <br> <sf:select path="listQA[${i.index}].question">				
				<sf:options items="${listOfQuestion}"></sf:options>
				</sf:select> 
				<br> 
				<sf:input path="listQA[${i.index}].question" type="text" 
					name="Value" placeholder="Security Answer " value="" />
				<br> 
				</c:forEach>
				<input type="checkbox" name="agree" value="agree">
				I accept <span>UD's terms and conditions</span> 
				<br> 
				<input class="button" id="registerButton" type="submit" value="Register">
			</fieldset>
		</sf:form>
	</div>
</body>
<footer>
<div class="header">
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
	</div>
</footer>
</html>
