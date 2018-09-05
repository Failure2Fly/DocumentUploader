<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"><%-- integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> --%>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"> <%-- integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous"> --%>
<%--   <link rel="stylesheet" href="<c:url value="./webapp/CSS/global.css"/>" > --%>
  <link rel="stylesheet" href="CSS/global.css">
  <title>DocumentUploader Register</title>
</head>

<body>
	<h1>This is the Register Page</h1>
	
	<h2>Register</h2>
		<div id="register-form" class="form register-form">
			<form action="" method="post">
				<fieldset class="register-form">
					<input type="text" class="fas" id="fields" name="firstname" placeholder="&#xf406; First name " value=""> <br>
					<input type="text" class="fas" id="fields" name="lastname" placeholder="&#xf406; Last name" value=""> <br>
					<input type="text" class="fas" id="fields" name="username" placeholder="&#xf406; Create a username" id="username"> <br>
					<input type="text" class="fas" id="fields" name="email" placeholder="&#xf0e0; Email" id="email"> <br>
					<input type="text" class="fas" id="fields" name="password" placeholder="&#xf023; Create a password" id="password"> <br>
					<br>
					<select name="securityQuestion" form="registerCred">
					  <option value="maiden">What is the maiden name of your mother?</option>
					  <option value="pet">What is the name of your first pet?</option>
					  <option value="concert">What was the first concert you attended?</option>
					  <option value="car">What was the make and model of your first car?</option>
					</select>
					<br>
					<input type="text" class="fas" id="fields" name="securityQuestion" placeholder="Security Answer " value=""> 
					<br>
					<input type="checkbox" name="agree" value="agree" class="form-check">I accept <span>UD's terms and conditions</span>
					<input class="button" id="registerButton" type="submit" value="Register">
					
					
				</fieldset>
			</form>
		</div>

</body>

<footer>
  <div class="row">
    <div class="col-4 social">
      <i class="fab fa-facebook-f"></i>
      <i class="fab fa-twitter"></i>
      <i class="fab fa-linkedin-in"></i>
      <i class="fab fa-google-plus-g"></i>
      <i class="fab fa-youtube"></i>

    </div>
    <div class="col-8 copyright">
      <p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
    </div>
  </div>
</footer>
</html>