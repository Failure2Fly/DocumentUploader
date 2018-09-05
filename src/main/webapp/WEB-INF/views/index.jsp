<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
  <link rel="stylesheet" href="<c:url value="./resources/CSS/global.css"/>" />
  <title>TP Register</title>
</head>

<body>
  <nav class="navbar navbar-expand-lg">
    <div class="container logo">
      <a class="navbar-brand" href="#"><img src="<c:url value="./resources/imgs/logo-TP.jpg"/>" /></a>
    </div>
    <div class="nav-right">
      <div class="nav phone">
        <i class="fas fa-phone"></i>
        <p> Call TP 800 800 8000</p>
      </div>
      <div class="nav-buttons">
        <a href="/TradingPlatform/login"><button class="btn btn-primary" type="submit">Login</button></a>
        <a href="/TradingPlatform/register"><button class="btn btn-primary" type="submit">Register</button></a>
      </div>
    </div>
  </nav>
  <div  class="main-img" style="background-image: url(./resources/imgs/main.jpg)">
  </div>
<!--	
	<div class="overlay">
		<h1>Why Choose TP?</h1>
		<p>Manage your own investments on your own time. TP website's powerful, 
		user-friendly stock trading tools designed to provide everything you need for online trading.
		<span>OPEN AN ACCOUNT</span></p>
	</div> 
-->
	
<div class="internal-nav">
  <ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Contact</a>
    </li>
  </ul>
  <div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
      <div class="tab-items">
        <i class="fas fa-quote-left fa-3x"></i>
        <div class="tab-desc">
          <h5>Quotes</h5>
          <h5>Quotes</h5>
        </div>
      </div>
      <div class="tab-items">
        <i class="fas fa-mobile-alt fa-3x"></i>
        <div class="tab-desc">
          <h5>Phone</h5>
          <h5>Phone</h5>
        </div>
      </div>
      <div class="tab-items">
        <i class="fas fa-th-large fa-3x"></i>
        <div class="tab-desc">
          <h5>Squares</h5>
          <h5>Squares</h5>
        </div>
      </div>
      <div class="tab-items">
        <i class="fas fa-boxes fa-3x"></i>
        <div class="tab-desc">
          <h5>Blocks</h5>
          <h5>Blocks</h5>
        </div>
      </div>
      <div class="tab-items">
        <i class="far fa-money-bill-alt fa-3x"></i>
        <div class="tab-desc">
          <h5>Money</h5>
          <h5>Money</h5>
        </div>
      </div>
      <div class="tab-items-last">
        <i class="far fa-credit-card fa-3x"></i>
        <div class="tab-desc">
          <h5>Credit</h5>
          <h5>Card</h5>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">

    </div>
    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">

    </div>
    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">

    </div>
    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">

    </div>
    <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">

    </div>
  </div>
</div>
  
  <div>
  	<div class="main-four">
	  	<div class="main-single">
	  		<img src="<c:url value="./resources/imgs/round-images1.png"/>"/>
	  		<div class="main-four-inner-text">
	  			<div>
	  				<h3>Online Trading</h3>
	  				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at dolor ac velit 
	  				efficitur dictum eget convallis lacus. Donec molestie dictum lorem cursus suscipit. 
	  				Donec sollicitudin pulvinar congue. Vivamus ligula libero, facilisis in dolor sit amet, 
	  				tristique convallis lectus. Maecenas viverra sem et leo gravida, laoreet pretium augue 
	  				vestibulum. Maecenas sit amet nisl leo. Etiam rutrum molestie imperdiet. Fusce dapibus 
	  				sem et nulla rutrum, a venenatis neque malesuada.</p>
	  			</div>
	  			<button type="submit" class="btn btn-primary btn-sign-in">Learn more</button>
	  		</div>
	  	</div>
	  	<div class="main-single">
	  		<img src="<c:url value="./resources/imgs/round-images2.png"/>"/>
	  		<div>
	  			<div>
	  				<h3>Investment Products</h3>
	  				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at dolor ac velit 
	  				efficitur dictum eget convallis lacus. Donec molestie dictum lorem cursus suscipit. 
	  				Donec sollicitudin pulvinar congue. Vivamus ligula libero, facilisis in dolor sit amet, 
	  				tristique convallis lectus. Maecenas viverra sem et leo gravida, laoreet pretium augue 
	  				vestibulum. Maecenas sit amet nisl leo. Etiam rutrum molestie imperdiet. Fusce dapibus 
	  				sem et nulla rutrum, a venenatis neque malesuada.</p>
	  			</div>
	  			<button type="submit" class="btn btn-primary btn-sign-in">Learn more</button>
	  		</div>
  		</div>
  	</div>
  	<div class="main-four">
	  	<div class="main-single">
	  		<img src="<c:url value="./resources/imgs/round-images3.png"/>"/>
	  		<div>
	  			<div class="main-four-inner-text">
	  				<h3>Online Brokerage</h3>
	  				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at dolor ac velit 
	  				efficitur dictum eget convallis lacus. Donec molestie dictum lorem cursus suscipit. 
	  				Donec sollicitudin pulvinar congue. Vivamus ligula libero, facilisis in dolor sit amet, 
	  				tristique convallis lectus. Maecenas viverra sem et leo gravida, laoreet pretium augue 
	  				vestibulum. Maecenas sit amet nisl leo. Etiam rutrum molestie imperdiet. Fusce dapibus 
	  				sem et nulla rutrum, a venenatis neque malesuada.</p>
	  			</div>
	  			<button type="submit" class="btn btn-primary btn-sign-in">Learn more</button>
	  		</div>
	  	</div>
	  	<div class="main-single">
	  		<img src="<c:url value="./resources/imgs/round-images4.png"/>"/>
	  		<div>
	  			<div>
	  				<h3>TP Services</h3>
	  				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed at dolor ac velit 
	  				efficitur dictum eget convallis lacus. Donec molestie dictum lorem cursus suscipit. 
	  				Donec sollicitudin pulvinar congue. Vivamus ligula libero, facilisis in dolor sit amet, 
	  				tristique convallis lectus. Maecenas viverra sem et leo gravida, laoreet pretium augue 
	  				vestibulum. Maecenas sit amet nisl leo. Etiam rutrum molestie imperdiet. Fusce dapibus 
	  				sem et nulla rutrum, a venenatis neque malesuada.</p>
	  			</div>
	  			<button type="submit" class="btn btn-primary btn-sign-in">Learn more</button>
	  		</div>
	  	</div>
  	</div>
  </div>



  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
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
      <p>&copy; 2018 TP tradingplatform.com. All right reserved.</p>
    </div>
  </div>
</footer>

</html>