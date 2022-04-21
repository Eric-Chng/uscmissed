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
		<div class = "leftSidebar">
			<img src = "images/logo.png">
			<div class = "link">Home</div>
			<div class = "link" id ="signin">Account Login</div>
			<div class = "link">Contact Us</div>
			<div class = "submitPost">Submit Post</div>
		</div>
		<div class = "rightSidebar">
			<input type="text" placeholder="Search..">
			<div class = "staticText">Trending Posts</div>
			<div class = "trendingContainer">
				<div class = "post"></div>
			</div>
		</div>
	</main>
</body>
</html>