<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<%-- integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> --%>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css">
<%-- integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"> --%>
<link rel="stylesheet" href="<c:url value="./CSS/global.css"/>">

<title>DocumentUploader Register</title>
</head>

<body>

	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">Navbar</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
	    <ul class="navbar-nav">
		<li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Link</a>
	      </li>
	      <li>
	      	<div class="g-signin2" data-onsuccess="onSignIn"></div>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          Dropdown
	        </a>
	        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
	    </ul>
	  </div>
	</nav>
      
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
				
				<br> <br> 
				<select >				
				   <c:forEach var="item" items="${listOfQuestion}">
    				 <option>${item}</option>
    			   </c:forEach>
				</select> 
				<br> 
				<input  type="text" 
					name="Value" placeholder="Security Answer " />
				<br> 
				<input type="checkbox" name="agree" value="agree">
				I accept <span>UD's terms and conditions</span> 
				<br> 
				<input class="button" id="registerButton" type="submit" value="Register">
			</fieldset>
		</sf:form>
	</div>
	<script src="./JS/global.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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