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
<title>DocumentUploader Home</title>
</head>

<body>
	<h1>Document Uploader</h1>
	
	<nav class="navbar navbar-expand-lg">
    <div class="container logo">
      <a class="navbar-brand" href="#"><img src="<c:url value=""/>" /></a>
    </div>
    <div class="nav-right">
      <div class="nav phone">
        <i class="fas fa-phone"></i>
        <p> Call DU 800 800 8000</p>
      </div>
      <div class="nav-buttons">
        <a href="/DocumentUploader/login"><button class="btn btn-primary" type="submit">Login</button></a>
        <a href="/DocumentUploader/register"><button class="btn btn-primary" type="submit">Register</button></a>
      </div>
    </div>
  </nav>
	
	<div  class="main-img" style="background-image: url(./webapp/imgs/main.jpg)">
  </div>
  
  <div>
  </div>
	

  
</body>

<footer>
	<div class="row">
		<div class="col-4 social">
			<i class="fab fa-facebook-f"></i> <i class="fab fa-twitter"></i> <i
				class="fab fa-linkedin-in"></i> <i class="fab fa-google-plus-g"></i>
			<i class="fab fa-youtube"></i>

		</div>
		<div class="col-8 copyright">
			<p>&copy; 2018 DU documentuploader.com. All right reserved.</p>
		</div>
	</div>
</footer>

</html>