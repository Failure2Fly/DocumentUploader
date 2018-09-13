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
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="./CSS/global.css"/>">
<title>Document Uploader</title>
<script>
function displayErrors(){
	if(document.getElementById("loginErrors")==""){
		document.getElementById("loginErrors").style.visibility='hidden';
	}else{
		document.getElementById("loginErrors").style.visibility='visible';
	}
}
</script>
</head>

<body onload="displayErrors()" class="register-login-background">

<nav class="navbar fixed-top navbar-expand-lg">
	  <a class="navbar-brand" href="/DocumentUploader">YORDU</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
	    <ul class="navbar-nav">
		<li class="nav-item active">
	        <a class="nav-link" href="/DocumentUploader/login"><button class="btn btn-primary" type="submit">Login</button> <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="/DocumentUploader/register"><button class="btn btn-primary" type="submit">Register</button></a>
	      </li>
	      <li>
	      	<div class="g-signin2" data-onsuccess="onSignIn"></div>
	      </li>
	      
	    </ul>
	  </div>
	</nav>
	
	
	<div class="register-form login-form">
<h3>Login</h3>
	<sf:form commandName="userAccount" method="POST" action="login">
	
	  <div class="form-row">
		   <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectUsername"><i class="fas fa-user"></i></div>
		        </div>
		        <sf:input path="username" type="text" name="UserName" class="form-control username" id="inlineFormInputGroup" placeholder="Username"/>
		        
		   </div>
		   <p class="validate-form" id="missingUsername">Please Enter A Username</p>
       	   <p class="validate-form" id="nonValidUsername">Please Enter A Valid Username</p>
		   <div class="input-group mb-2">
		        <div class="input-group-prepend">
		          <div class="input-group-text" id="incorrectPassword"><i class="fas fa-lock"></i></div>
		        </div>
		        <sf:input path="password" type="password" name="Password" class="form-control password" id="inlineFormInputGroup" placeholder="Password"/>
		   </div>
		   <p class="validate-form" id="missingPassword">Please Enter A Password</p>
           <p class="validate-form" id="nonValidPassword">Please Enter A Valid Password</p>
		  <div class="form-group-login">
		    <div class="form-check">
		      <input class="" type="checkbox" id="gridCheck">
		      <label class="form-check-label" for="gridCheck">
		        Remember me
		      </label>
		    </div>
		    <button type="submit" class="btn btn-primary btn-sign-in" value="Login">Login</button>
		  </div>
		 </div>
		 <p id="loginErrors" class="incorrect">${sessionScope.loginError}</p>
		<div class="form-row">
		  <div class="form-group-links">
		  	<div class="login-links">
		  		<a href="#">Lost your password?</a>
		  	</div>
		  </div>
	   </div>
	</sf:form>
</div>
	
	

	<script src="./JS/global.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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