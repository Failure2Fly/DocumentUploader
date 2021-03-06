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
<script>
	function DisplayAccounts() {

		var myObj, i, j, x = "";

		myObj = JSON.parse(document.getElementById("accountList").innerHTML);

		for (i in myObj) {
			x += "<h3><a href=\"/DocumentUploader/accountHome/"+myObj[i].businessAccountId+"\">"
					+ "Account ID: "+myObj[i].businessAccountId
					+ "<br> Account Name: "
					+ myObj[i].accountName
					+ "</a></h3>";
			x += "<br>";
		}

		document.getElementById("Accounts").innerHTML = x;

		/* var text = "My Button"; // JavaScript string
		button.setText(text); // text is converted to java.lang.String */

	}

</script>
<title>Document Uploader</title>
</head>

    <nav class="navbar fixed-top navbar-expand-lg">
	  <a class="navbar-brand" href="/DocumentUploader">YORDU</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
	    <ul class="navbar-nav">
		  <li class="nav-item active">
	        <a href="/DocumentUploader/createAccount"><button class="btn btn-primary" type="submit">CreateAccount</button></a>
	      </li>
	      <li class="nav-item">
	        <a href="/DocumentUploader/userDetails"><button class="btn btn-primary" type="submit">User Details</button></a>
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

<body onload="DisplayAccounts()">
	<div class="header user-hello">
		<div class="mainTitle">
			<h1>Welcome to Document Loader, ${sessionScope.user.username}</h1>
		</div>
	</div>
	
	<div class="registration">
				
		<p id="Accounts">AccountsPlaceholder</p>


	</div>


	<p class="accountList" id="accountList">${sessionScope.accountList}</p>

	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">First</th>
	      <th scope="col">Last</th>
	      <th scope="col">Handle</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Mark</td>
	      <td>Otto</td>
	      <td>@mdo</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Jacob</td>
	      <td>Thornton</td>
	      <td>@fat</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Larry</td>
	      <td>the Bird</td>
	      <td>@twitter</td>
	    </tr>
	  </tbody>
	</table>


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

</body>
</html>