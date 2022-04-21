<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/sidebar.css">
<meta charset="UTF-8">
<title>Sidebar</title>
</head>
<body>
	<main> 
		<div id="leftSidebar">
            <a href="homepage.jsp"><img src = "images/logo.png"></a>
            <div class="link-current"><a href="homepage.jsp">Home</a></div>
            <div class="link" id ="signin">Account Login</div>
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