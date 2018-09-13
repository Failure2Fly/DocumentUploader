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
<script>
	function displayUsers() {

		var myObj, i, j, x = "";

		myObj = JSON.parse(document.getElementById("accountJson").innerHTML);

		for (i in myObj.userAccounts) {
			x += "<h3>" + "Username: " + myObj.userAccounts[i].username
					+ "</h3>";
			x += "<br>";
		}
		if (document.getElementById("errorText").innerHTML == "") {
			removeError.style.visibility = 'hidden';
			addError.style.visibility = 'hidden';
		} else if (document.getElementById("errorText").innerHTML == "That user is the owner and cannot be removed!") {
			removeError.style.visibility = 'visible';
			addError.style.visibility = 'hidden';
		} else if (document.getElementById("errorText").innerHTML == "Your repository cannot support more users at your service level!"
				|| document.getElementById("errorText").innerHTML == "This user has already been added!") {
			addError.style.visibility = 'visible';
			removeError.style.visibility = 'hidden';
		}

		document.getElementById("userList").innerHTML = x;
		/* var text = "My Button"; // JavaScript string
		button.setText(text); text is converted to java.lang.String */
	}
</script>
<title>Repository Details</title>
</head>


    <nav class="navbar fixed-top navbar-expand-lg">
	  <a class="navbar-brand" href="/DocumentUploader">YORDU</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
	    <ul class="navbar-nav">
		  <li class="nav-item active">
	        <a href="/DocumentUploader/userHome"><button class="btn btn-primary" type="submit">User Homepage</button></a>
	      </li>
	      <li class="nav-item">
	        <a href="/DocumentUploader/accountHome/${sessionScope.account.businessAccountId}"><button class="btn btn-primary" type="submit">Account Home</button></a>
	      </li>
	      <li class="nav-item">
			<a href="/DocumentUploader/login"><button class="btn btn-primary" type="submit">Logout</button></a>
	      </li>
	      <li>
	      	<div class="g-signin2" data-onsuccess="onSignIn"></div>
	      </li> 
	    </ul>
	  </div>
	</nav>
<body onload="displayUsers()">

	<div class="header user-hello">
		<div class="mainTitle">
			<h1>${sessionScope.account.accountName}Repository Details</h1>
		</div>
<<<<<<< HEAD
=======
		<div class="mainHeaderRight">
			<a href="/DocumentUploader/userHome">
				<button class="btn btn-primary" type="submit">Back to User
					Homepage</button>
			</a> <a
				href="/DocumentUploader/repositoryHome/${sessionScope.account.businessAccountId}">
				<button class="btn btn-primary" type="submit">Repository
					Home</button>
			</a> <a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Logout</button>
			</a>
		</div>
>>>>>>> dfb09030d439cac7b0aac4db120aaafcc1e6d696
	</div>

	<div id="update-form" class="update">
		<sf:form method="POST"
			action="/DocumentUploader/repositoryDetails/delete">
			<legend>Delete Repository:
				${sessionScope.account.accountName}</legend>
			<fieldset>
<<<<<<< HEAD
				<p>Do you want to delete the repository? </p>
			<!--<input style="margin-top: 0px;"type="checkbox" name="remove"> -->
				<br>
				<br> <input class="button" id="updateButton" type="submit"
					value="Delete">
		</fieldset>
=======
				<p>Do you want to delete the repository?</p>
				<input style="marigin-top: 0px;" type="checkbox" name="remove">
				<br> <br> <input class="button" id="updateButton"
					type="submit" value="Delete">
			</fieldset>
>>>>>>> dfb09030d439cac7b0aac4db120aaafcc1e6d696
		</sf:form>
		<p>Users currently attached to this repository:</p>
		<p id="userList">Placeholder User</p>
		<sf:form method="POST"
			action="/DocumentUploader/repositoryDetails/addUser">
			<p class="incorrect" id="addError">${sessionScope.repositoryDetailsError}</p>

			<legend>Add User to Repository:</legend>
			<fieldset>
				<input type="text" class="inputField" id="addInput" name="add"
					type="text" placeholder="Enter username"> <br> <br>
				<input class="button" id="addButton" type="submit" value="Add">
			</fieldset>
		</sf:form>

		<sf:form method="POST"
			action="/DocumentUploader/repositoryDetails/removeUser">
			<p class="incorrect" id="removeError">${sessionScope.repositoryDetailsError}</p>
			<legend>Remove User from Repository:</legend>
			<fieldset>
				<input type="text" class="inputField" id="removeInput" name="remove"
					type="text" placeholder="Enter username"> <br> <br>
				<input class="button" id="removeButton" type="submit" value="Remove">
			</fieldset>
		</sf:form>
		<sf:form method="POST"
			action="/DocumentUploader/repositoryDetails/changeName">
			<legend>Change Repository Name:</legend>
			<fieldset>
				<input name="accountName" type="text" class="inputField"
					id="changeName" type="text" placeholder="Enter new Account Name" />
				<br> <br> <input class="button" id="changeName"
					type="submit" value="Change">
			</fieldset>
		</sf:form>
		<div id="register-form" class="registration">

			<sf:form method="POST"
				action="/DocumentUploader/repositoryDetails/changeLevel">
				<legend>Change account service level:</legend>
				<fieldset>
					<select name="level">
						<c:forEach var="item" items="${sessionScope.listOfLevels}">
							<option>${item}</option>
						</c:forEach>
					</select> <br> <input class="button" id="changeLevel" type="submit"
						value="Change Level">

				</fieldset>
			</sf:form>
		</div>
	</div>
	<p class="hiddenText" id="accountJson">${sessionScope.accountDetailJson}</p>
	<p id="errorText">${sessionScope.repositoryDetailsError}</p>
</body>
<footer>
	<div class="header">
	<div class="row footer-row">
		<div class="col-6 social">
			<a class="fab fa-facebook-f" href="https://www.facebook.com"></a> <a
				class="fab fa-twitter" href="https://www.twitter.com"></a> <a
				class="fab fa-linkedin-in" href="https://www.linkedin.com"></a> <a
				class="fab fa-google-plus-g" href="http://www.plus.google.com"></a>
			<a class="fab fa-youtube" href="http://www.youtube.com"></a>

		</div>
		<div class="col-6 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
	</div>
</footer>
</html>