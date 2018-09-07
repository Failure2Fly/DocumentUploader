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

<title>DocumentUploader Business Account Creation</title>
</head>

<body>
	<div class="header"><div class = "registrationTitle"><h1>${sessionScope.user.username} Business Account Creation</h1></div></div>
	

	<h2>Create Account</h2>

	<div id="register-form" class="registration">
		<sf:form commandName="businessAccount" method="POST"  action="createAccount">
			<fieldset >
					<p>Account name:</p>
				<sf:input path="accountName" class="inputField" type="text" 
					name="AccountName" placeholder="Account Name " value="" />
				<br>
				<br> <br> <select>
					<option value="BRONZE">Bronze</option>
					<option value="SILVER">Silver</option>
					<option value="GOLD">Gold</option>
					<option value="UNLIMITED">Unlimited</option>
					<option value="ENTERPRISE">Enterprise</option>
				</select> 
				<br> 
				
				
				<input class="button" id="registerButton" type="submit" value="Create Account">


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
