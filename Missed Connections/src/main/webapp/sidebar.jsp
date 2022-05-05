<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sidebar.css">
<meta charset="UTF-8">

<!-- START COPYING HERE  -->
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="424408738453-c9qt61qb2pfac1rk37s7mpda1gfksef4.apps.googleusercontent.com">
  <script src="https://apis.google.com/js/api:client.js"></script>
 <script>
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init({
        client_id: '424408738453-c9qt61qb2pfac1rk37s7mpda1gfksef4.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
      });
      attachSignin(document.getElementById('signin'));
    });
  };

  function attachSignin(element) {
    auth2.attachClickHandler(element, {},
        function(googleUser) {
    	  var email = googleUser.getBasicProfile().getEmail();
    	  var name = googleUser.getBasicProfile().getName().split(' ').join('=');
    	  var url = email.substring(email.indexOf('@') + 1);
		  if(url != "usc.edu"){
			  alert("The account you used is not a USC email. Please sign in with your USC email.");
			  var auth2 = gapi.auth2.getAuthInstance();
			  auth2.disconnect();
		  }
		  else {
		  	   window.location.href = "GoogleDispatcher?name="+name+"&email="+email;
		  }
        }, function(error) {
           alert("Sign in not completed, please try again.");
        });
  }
  </script>
  <!-- END COPYING HERE  -->
  
<title>Sidebar</title>
</head>
<body>
	<main> 
	  <script>startApp();</script>
		<div id="leftSidebar">
            <a href="homepage.jsp"><img src = "images/logo.png"></a>
            <div class="link-current"><a href="homepage.jsp">Home</a></div>
            <!-- THE LINE BELOW HAS ALSO BEEN CHANGED   -->
            <div class="customGPlusSignIn" id = "signin" >Account Login</div>
            <div class="link"><a href="contactus.jsp">Contact Us</a></div>
            <div class = "submitPost">Submit Post</div>
        </div>
		<div id="rightSidebar">
            <input type="text" placeholder="Search.."><button type="submit" id="search-button"><i class="fa fa-search"></i></button>
            <div class = "staticText">Trending Posts</div>
            <div class = "trendingContainer">
                <div class = "post"></div>
            </div>
        </div>
	</main>
</body>
</html>