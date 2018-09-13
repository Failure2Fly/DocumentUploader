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
<link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="./CSS/global.css"/>">
<link rel="stylesheet" href="<c:url value="./CSS/serviceLevel.css"/>">
<title>Document Uploader</title>
</head>

<body>
	<div class="header">
		<div class="mainTitle"><h1>Ye Olde Renaissance Document Uploader</h1></div>
		<div class="mainHeaderRight">
			<a href="/DocumentUploader/login">
				<button class="btn btn-primary" type="submit">Login</button>
			</a> 
			<a href="/DocumentUploader/register">
				<button	class="btn btn-primary" type="submit">Register</button>
			</a>
		</div>
	</div>

	<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMhn1E6Nca2jgQ0EMfcmV0hp_SS_pO9t0yOdEzHmbAq1CinOr-"/>
	
	<div class="row row-3">

            
            <div class="column column-3 column-left">
                <p class="perks membership perks-top">Bronze</p>
                <p class="fee membership">Free</p>
                <p class="perks membership perks-bottom">Forever</p>
                <p class="perks">Maximum 2 documents at any time</p>
                <p class="perks">Can upload 2 documents per month</p>
                <p class="perks">1 user</p>
                <p class="perks">Adverts</p>
                <div class="choose">
                    <button class=button type="button" onclick="alert('Thank You!')">Choose</button>
                </div>
            </div>
            <div class="column column-3 column-center">
                <p class="perks membership membership-2 perks-top">Silver</p>
                <p class="fee membership membership-2">$1</p>
                <p class="perks membership membership-2 perks-bottom">per month</p>
                <p class="perks">Maximum 5 documents at any time</p>
                <p class="perks">Can upload 10 documents per month</p>
                <p class="perks">1 user</p>
                <p class="perks">Adverts</p>
                <div class="choose">
                    <button class="button" type="button" onclick="alert('Thank You!')">Choose</button>
                </div>
            </div>
            <div class="column column-3 column-right">
                <p class="perks membership membership-3 perks-top">Gold</p>
                <p class="fee membership membership-3">$2</p>
                <p class="perks membership membership-3 perks-bottom">per month</p>
                <p class="perks">Maximum 20 documents at any time</p>
                <p class="perks">Can upload 50 documents per month</p>
                <p class="perks">2 user</p>
                <p class="perks">No Adverts</p>
                <div class="choose">
                    <button class=button type="button" onclick="alert('Thank You!')">Choose</button>
                </div>
            </div>
            <div class="column column-3 column-bottom-left">
                <p class="perks membership membership-4 perks-top">Unlimited</p>
                <p class="fee membership membership-4">$5</p>
                <p class="perks membership membership-4 perks-bottom">per month</p>
                <p class="perks">Unlimited documents at any time</p>
                <p class="perks">Unlimited document uploads per month</p>
                <p class="perks">10 user ($1 for each addition 10 Users)</p>
                <p class="perks">No Adverts</p>
                <div class="choose">
                    <button class=button type="button" onclick="alert('Thank You!')">Choose</button>
                </div>
            </div>
            <div class="column column-3 column-bottom-right">
                <p class="perks membership membership-5 perks-top">Enterprise</p>
                <p class="fee membership membership-5">$15</p>
                <p class="perks membership membership-5 perks-bottom">per month</p>
                <p class="perks">Unlimited documents at any time</p>
                <p class="perks">Unlimited document uploads per month</p>
                <p class="perks">200 user ($1 for each addition 20 Users)</p>
                <p class="perks">No Adverts</p>
                <div class="choose">
                    <button class=button type="button" onclick="alert('Thank You!')">Choose</button>
                </div>
            </div>
        </div>





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