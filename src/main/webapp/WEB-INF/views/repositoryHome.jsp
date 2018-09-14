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
	
<link href="font/font-fileuploader.css" media="all" rel="stylesheet">
<link href="jquery.fileuploader.min.css" media="all" rel="stylesheet">

<link rel="stylesheet" href="<c:url value="/CSS/global.css"/>">

<script>
	function DisplayFiles() {

		var myObj, i, x = "", y = "";
		x = document.getElementById("fileList").innerHTML;
		x = x.replace(/\\/g, "\\\\");
		myObj = JSON.parse(x);

		for (i in myObj) {
			y += "<a href=\"/DocumentUploader/downloadFile/"+myObj[i].repositoryPath+"\"><h3><p>"
					+ "File name: "
					+ myObj[i].name
					+ " "
					+ "Upload date: "
					+ myObj[i].date + "</p></h3></a>";
			y += "<a href=\"/DocumentUploader/deleteFile/"+myObj[i].repositoryPath+"\"><h3><p>"
					+ "Delete" + "</p></h3></a>";
			y += "<br>";
		}
		if (document.getElementById("uploadErrorText").innerHTML != "") {
			uploadErrorText.style.visibility = 'visible';
		} else {
			uploadErrorText.style.visibility = 'hidden';
		}
		document.getElementById("Documents").innerHTML = y;
		/* var text = "My Button"; // JavaScript string
		button.setText(text); // text is converted to java.lang.String */
	}
	
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

<title>Document Uploader</title>
</head>

<nav class="navbar fixed-top navbar-expand-lg">
	<a class="navbar-brand" href="/DocumentUploader">YORDU</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse justify-content-end"
		id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item active"><a href="/DocumentUploader/userHome"><button
						class="btn btn-primary" type="submit">User Homepage</button></a></li>
			<li class="nav-item"><a
				href="/DocumentUploader/repositoryDetails"><button
						class="btn btn-primary" type="submit">Repository Details</button></a>
			</li>
			<li class="nav-item"><a href="/DocumentUploader/login"><button
						class="btn btn-primary" type="submit">Logout</button></a></li>
			<li>
				<div class="g-signin2" data-onsuccess="onSignIn"></div>
			</li>
		</ul>
	</div>
</nav>


<body onload="DisplayFiles(); displayAdverts();">


	<div class="header user-hello">
		<div class="mainTitle">
			<h1>${sessionScope.account.owner.username}'s
				${sessionScope.account.serviceLevel.serviceLevel} Repository,
				${sessionScope.account.accountName }</h1>
		</div>
	</div>
	
	<img id="hasAdverts" class="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMhn1E6Nca2jgQ0EMfcmV0hp_SS_pO9t0yOdEzHmbAq1CinOr-"/>

	<div id="register-form" class="registration">
		<sf:form commandName="file" method="POST"
			action="${sessionScope.account.businessAccountId }"
			enctype="multipart/form-data">
			<p class="incorrect" id="uploadErrorText">${sessionScope.accountHomeError}</p>
			<p>Choose a file to upload:</p>
			<input name="file" id="sourcePath" type="file" class="inputField" />
			<input onclick="refreshPage()" class="button" id="registerButton"
				type="submit" value="Upload" class="inputField">
		</sf:form>
	</div>

	<div class="registration">

		<p id="Documents">DocumentsPlaceholder</p>


	</div>



	<p class="hiddenText" id="fileList">${sessionScope.fileList}</p>
	<p class="hiddenText" id="accountHomeErrorText">${sessionScope.accountHomeError}</p>
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