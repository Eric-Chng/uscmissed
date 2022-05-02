<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta
	content="438412949308-d20tpeitmhc1horfk1903min2aqp0bk8.apps.googleusercontent.com"
	name="google-signin-client_id">
	
	
<title>Login / Register</title>
<link href="https://fonts.googleapis.com" rel="preconnect">
<link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
	rel="stylesheet">
<script crossorigin="anonymous"
	src="https://kit.fontawesome.com/3204349982.js"></script>
<script async defer src="https://apis.google.com/js/platform.js"></script>
<link href="index.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">
<script src="https://apis.google.com/js/api:client.js"></script>
<style>
input.inputfield {
	width: 70%;
	height: 28px;
	margin-top: 6px;
	margin-bottom: 6px;
}

form button {
	width: 25pc;
	height: 28px;
	border: 3px red;
	border-radius: 4px;
	background: red;
	color: white;
}

.column {
	float: left;
	width: 45%;
	padding: 2p%;
	margin-left: 5%;
	align: center;
}

.row:after {
	content: "";
	display: table;
	clear: both;
}

#left {
	align: center;
}

#right {
	align: center;
}
</style>
</head>
<body>
<p>
<%
if (request.getAttribute("loginresponse") != null) {
out.println("<div class ='error' style='padding: 45px;'>");
out.println("Error: " + request.getAttribute("loginresponse") + " Please try again");
out.println("</div>");
}
%>
</p>

	<div id="top">
		<div id="saleats">
			<p>
				<a href="index.jsp">SalEats!</a>
			</p>
		</div>
		
		<div class="links">
			<p>
				<a href="auth.jsp">Login/Register</a>
			</p>
			</div>
			<div class="links">
			<p>
				<a href="index.jsp">Home</a>
			</p>
		</div>
	</div>
	<hr style="width: 100%; float: center; border-top: gray;">
	<div class="row">
		<div class="column">
			<div id="form">
				<div id="left">

					<form id="login" action="LoginDispatcher" method="GET"
						onsubmit="return validate()">
						<h1>Login</h1>
						<label for="user_email">Email</label> </br> <input class="inputfield"
							type="email" id="user_email" name="login_email" required> </br> <label
							for="user_password">Password</label> </br> <input class="inputfield"
							type="text" id="user_password" name="login_password" required> </br> </br>
						<button type="submit" value="signin">Sign In</button>
					</form>


					<div>
						
							<div class="g-signin2" data-onsuccess="onSignIn" data-longtitle="true" data-theme="dark" data-width="400"></div>
						
					</div>

				</div>
			</div>
		</div>
		<div class="column">
			<div id="right">
				<form id="register" action="RegisterDispatcher" method="GET">
					<h1>Register</h1>
					<label for="user_email">Email</label> </br> <input class="inputfield"
						type="email" id="user_email" name="user_email" required> </br> <label
						for="user_name">Name</label> </br> <input class="inputfield"
						type="text" id="user_name" name="user_name" required> </br> <label
						for="user_password">Password</label> </br> <input class="inputfield"
						type="text" id="user_password" name="user_password" required> </br> <label
						for="confirm_user_password">Confirm Password</label> </br> <input
						class="inputfield" type="text" id="confirm_user_password" name="confirm_user_password" required>
					</br> <label id="TOS"><input type="checkbox">I have read
						and agree to the terms and conditions of SalEats.</label> </br> </br>
					<button type="submit" value="register">Create Account</button>
				</form>
			</div>
		</div>
	</div>
	
   
  <script>
  function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log("Name=" + profile.getName());
	  var name = profile.getName();
	  var email = profile.getEmail();
	  //prevent staying signed in 
	   var auth2 = gapi.auth2.getAuthInstance();
	  auth2.disconnect(); 
	  auth2.signOut();
	  googleUser.disconnect();
	  
	  window.location.href = "GoogleDispatcher?name=" + name + "&email=" + email + "&password=googleinternshipplease";

	  return false;
  }
  </script>

</body>
</html>