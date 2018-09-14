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

<title>User Details</title>
<script>
	function displayAdverts(){
		
		var myObj, i, j, x = "";
	
		myObj = JSON.parse(document.getElementById("accountList").innerHTML);
	
		for (i in myObj){
			
			var adverts = myObj[i].serviceLevel.hasAdverts
			var img = document.getElementById("hasAdverts");
			
			if( adverts === false ){
				img.classList.add("validate-form");
			}
	
		}
	}
</script>
</head>

    <nav class="navbar fixed-top navbar-expand-lg">
	  <a class="navbar-brand" href="/DocumentUploader">
		<img src="./IMG/yordu_logo.png"/></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
	    <ul class="navbar-nav">
		  <li class="nav-item active">
	        <a href="/DocumentUploader/userHome"><button class="btn btn-primary" type="submit">Back to Homepage</button></a>
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

<body onload="displayAdverts()">
	<div class="header user-hello">
		<div class="mainTitle">
			<h1>Change your details, ${sessionScope.user.username}</h1>
		</div>
	</div>
	
	<img id="hasAdverts" class="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMhn1E6Nca2jgQ0EMfcmV0hp_SS_pO9t0yOdEzHmbAq1CinOr-"/>
	
	<div id="update-form" class="update">
		<sf:form commandName="userAccount" method="POST" action="userDetails">
			<legend>Update:</legend>
			<fieldset>
				<p>Current First Name: <span>${sessionScope.user.firstName}</span></p>
				<sf:input path="firstName" class="inputField" type="text"
					name="FirstName" placeholder="New First name " value="" />
				<br>
				<br>
				<p>Current Last Name: <span>${sessionScope.user.lastName}</span></p>
				<sf:input path="lastName" class="inputField" type="text"
					name="LastName" placeholder="New Last name" value="" />
				<br>
				<br>
				<p>Current Email: <span>${sessionScope.user.userEmail}</span></p>
				<p class="incorrect">${sessionScope.accountDetailsEmailError }</p>
				<sf:input path="userEmail" class="inputField" type="text"
					name="Email" placeholder="New Email" />
				<br>
				<br>		
				<p>Current Password: <span>${sessionScope.user.password}</span></p>
				<p class="incorrect">${sessionScope.accountDetailsPasswordError }</p>
				<sf:input path="password" class="inputField" type="password"
					name="Password" placeholder="Create a new password" />
				<br>
				<br> <input class="button" id="updateButton" type="submit"
					value="Update">
			</fieldset>
		</sf:form>
	</div>
<p class="hiddenText" id="accountList">${sessionScope.accountList}</p>
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