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

	<nav class="navbar fixed-top navbar-expand-lg">
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
      
   <div class="register-form">
<h3>Register</h3>
	<sf:form commandName="userAccount" method="POST" action="register">
	
	  <div class="form-row">
		  <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectFirstName"><i class="fas fa-user"></i></div>
		        </div>
		        <sf:input path="firstName" type="text" name="FirstName" class="form-control first-name" placeholder="First name"/>
		   </div>
		   <p class="validate-form" id="missingFirstName">Please Enter First Name</p>
	  	  <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectLastName"><i class="fas fa-user"></i></div>
		        </div>
		        <sf:input path="lastName" type="text" name="LastName" class="form-control last-name" placeholder="Last name"/>
		   </div>
		   <p class="validate-form" id="missingLastName">Please Enter Last Name</p>
		   <div class="input-group mb-2">
		     <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectEmail"><i class="fas fa-envelope"></i></div>
		        </div>
		        <sf:input path="userEmail" type="text" name="Email" class="form-control email" placeholder="Email"/>
		   </div>
		   <p class="validate-form" id="missingEmail">Please Enter Email Address</p>
       <p class="validate-form" id="nonValidEmail">Please Enter a Valid Email Address</p>
		   <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorretUsername"><i class="fas fa-address-card"></i></div>
		        </div>
		        <sf:input path="username" type="text" name="UserName" class="form-control username" placeholder="Create a username"/>
		   </div>
		   <p class="validate-form" id="missingUsername">Please Enter A Username</p>
       <p class="validate-form" id="nonValidUsername">Please Enter A Valid Username</p>
		   <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectPassword"><i class="fas fa-lock"></i></div>
		        </div>
		        <sf:input path="password" type="password" name="Password" class="form-control create-password" placeholder="Create a password"/>
		   </div>
		   <p class="validate-form" id="missingPassword">Please Enter A Password</p>
       <p class="validate-form" id="nonValidPassword">Please Enter A Valid Password</p>
		   <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectConfirmPassword"><i class="fas fa-lock"></i></div>
		        </div>
		        <input type="password" name="PasswordConfirm" class="form-control confirm-password" placeholder="Confirm your password"/>
		   </div>
		   <p class="validate-form" id="missingConfirmPassword">Please Enter Confirm Password</p>
       <p class="validate-form" id="nonValidConfirmPassword">Passwords Do Not Match</p>
		     <div class="input-group mb-2">
            <select class="form-group security-question">				
				   <c:forEach var="item" items="${listOfQuestion}">
    				 <option>${item}</option>
    			   </c:forEach>
				</select> 
          </div>
          <div class="input-group mb-2">
            <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectSecurityQuestion"><i class="fas fa-lock"></i></div>
		        </div>
            <input type="text" class="form-control security-question" name="securityQuestion" placeholder="Security Answer " value="" />
          </div>
          <p class="validate-form" id="missingSecurityQuestion">Please Answer Security Question</p>
		  <div class="form-group">
		    <div class="form-check">
		      <input class="form-check-input" type="checkbox" id="gridCheck">
		      <label class="form-check-label" for="gridCheck">
		        I accept <a class="terms">TP's terms and conditions</a>
		      </label>
		    </div>
		    <button type="submit" class="btn btn-primary btn-sign-in" value="Register">Register</button>
		  </div>
	   </div>
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