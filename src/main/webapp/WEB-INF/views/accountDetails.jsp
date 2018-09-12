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

<title>Business Account Details</title>
</head>
<body>

	<div class="header">
		<div class="mainHeaderRight"></div>
		<div class="mainTitle">
			<h1>${sessionScope.account.accountName} Account Details</h1>
		</div>
		<div class="mainHeaderRight">
			<a href="/DocumentUploader/userHome">
				<button class="btn btn-primary" type="submit">Back to User
					Homepage</button>
			</a> <a href="/DocumentUploader/accountHome/${sessionScope.account.businessAccountId }">
				<button class="btn btn-primary" type="submit">Account
					Home</button>
			</a> <a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Logout</button>
			</a>
		</div>
	</div>

	<div id="update-form" class="update">
		<sf:form method="POST" action="/DocumentUploader/accountDetails/delete">
			<legend>Delete Repository: ${sessionScope.account.accountName}</legend>
			<fieldset>
				<p>Do you want to delete the repository? </p>
				<input style="marigin-top:0px;"type="checkbox" name="remove">
				<br>
				<br> <input class="button" id="updateButton" type="submit"
					value="Delete">
		</fieldset>
		</sf:form>
		<sf:form method="POST" action="/DocumentUploader/accountDetails/addUser">
				<legend>Add User to Repository:</legend>
			<fieldset>
				<input type="text" class="inputField" id="fields"name="add"type="text" 
				placeholder="Enter the username of the the user you would like to add">
				<br>
				<br> <input class="button" id="updateButton" type="submit"
					value="Add">
			</fieldset>
		</sf:form>
		<sf:form method="POST" action="/DocumentUploader/accountDetails/changeName">
				<legend>Change Repository Name:</legend>
			<fieldset>
				<input name="accountName" type="text" class="inputField" id="fields"  type="text" 
				placeholder="Enter new Account Name"/>
				<br>
				<br> <input class="button" id="updateButton" type="submit"
					value="Change">
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