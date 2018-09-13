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
			x += "<h3><a href=\"/DocumentUploader/repositoryHome/"+myObj[i].businessAccountId+"\">"
					+ "Repository ID: "+myObj[i].businessAccountId+" Service Level: "+myObj[i].serviceLevel.serviceLevel
					+ "<br> Repository Name: "
					+ myObj[i].accountName
					+ "</a></h3>";
			x += "<br>";
		}

		document.getElementById("Accounts").innerHTML = x;

		/* var text = "My Button"; // JavaScript string
		button.setText(text); // text is converted to java.lang.String */
		
	}
	function displayAdverts(){
		var serviceLevel = JSON.parse(document.getElementById("accountList").innerHTML);
		var img = documnent.getElementById("hasAdverts");
		
		if(serviceLevel.serviceLevel.hasAdverts == false){
			img.classList.remove("validate-form");
			return false;
		}
		else{
			return true;
		}
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
	        <a href="/DocumentUploader/createRepository"><button class="btn btn-primary" type="submit">Create Repository</button></a>
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

<body onload="DisplayAccounts(); displayAdverts();">
	<div class="header user-hello">
		<div class="mainTitle">
			<h1>Welcome to Document Loader, ${sessionScope.user.username}</h1>
		</div>
	</div>
	
	<img id="hasAdverts" class="validate-form" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMhn1E6Nca2jgQ0EMfcmV0hp_SS_pO9t0yOdEzHmbAq1CinOr-"/>
	
	<div class="registration">
				
		<p id="Accounts">AccountsPlaceholder</p>


	</div>


	<p class="hiddenText" id="accountList">${sessionScope.accountList}</p>

	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Account</th>
	      <th scope="col">Service Level</th>
	      <th scope="col"># of Users</th>
	      <th scope="col">User Limit</th>
	      <th scope="col"># of Documents</th>
	      <th scope="col">Document limit</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Don'tTouchDonny</td>
	      <td>Unlimited</td>
	      <td>4</td>
	      <td>10</td>
	      <td>30</td>
	      <td>unlimited</td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Mine</td>
	      <td>Silver</td>
	      <td>1</td>
	      <td>1</td>
	      <td>4</td>
	      <td>10 documents</td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>The Entourage</td>
	      <td>Unlimited</td>
	      <td>8</td>
	      <td>10</td>
	      <td>124</td>
	      <td>Unlimited</td>
	    </tr>
	  </tbody>
	</table>


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