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
<<<<<<< HEAD

<link rel="stylesheet" href="<c:url value="/CSS/global.css"/>">
=======
<%-- integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"> --%>
<link rel="stylesheet" href="<c:url value="../CSS/global.css"/>">
>>>>>>> 5a6b6be4535d572e44cd70b9d936995ce67e229d

<title>Business Account Details</title>
</head>

<<<<<<< HEAD

<body>
	<div class="header">
		<div class="mainHeaderRight"></div>
		<div class="mainTitle">
			<h1>Details for ${sessionScope.user.username}'s account,
				${sessionScope.account.accountName }</h1>
=======
<body onload="BusinessAccount()">
	<div class="header">
		<div class="mainHeaderRight"></div>
		<div class="mainTitle">
			<h1>Change your business account details,
				${sessionScope.user.username}</h1>
>>>>>>> 5a6b6be4535d572e44cd70b9d936995ce67e229d
		</div>
		<div class="mainHeaderRight">
			<a href="/DocumentUploader/userHome">
				<button class="btn btn-primary" type="submit">Back to
					Homepage</button>
			</a> <a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Logout</button>
			</a>


		</div>
	</div>
	<div>
		<sf:form  method="POST" action="/WEB-INF/views/accountDetails">
			<p>Choose a file to upload:</p>
			<sf:input path="sourcePath" id="sourcePath" type="file" />
			<input class="button" id="registerButton" type="submit" value="Upload">
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