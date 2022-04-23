<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MissedSC | Contact Form</title>
	<script src="https://kit.fontawesome.com/51b017a2ee.js" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="css/sidebar.css">
	<link rel="stylesheet" href="css/overlay.css">
	<style>
		@font-face {
           font-family: 'Adagio Sans';
           src: url('Adagio_Sans-Regular.eot');
           src: local('Adagio Sans'), local('Adagio_Sans-Regular'),
               url('Adagio_Sans-Regular.eot?#iefix') format('embedded-opentype'),
               url('Adagio_Sans-Regular.woff2') format('woff2'),
               url('Adagio_Sans-Regular.woff') format('woff'),
               url('Adagio_Sans-Regular.ttf') format('truetype');
           font-weight: normal;
           font-style: normal;
        }
		body {
			background-color: #FFF7F0;
			font-family: 'Adagio Sans';
		}
		#page_body {
			display: flex;
			width: 100%;
		}
		#main_col {
			margin-left: 27%;
			margin-right: 6%;
			width: 80%;
			float: right;
			display: inline-block;
		}
		#contact_us {
			margin: auto;
			margin-top: 60px;
			background-color: #D56262;
			width: 200px;
			color: white;
			text-align: center;
			border-radius: 300px;
		}
		.row {
			display: flex;
			margin-bottom: 5px;
			width: 100%;
		}
		.selection {
			/*text-align: center;*/
			width: 100%;
			padding: 5px;
		}
		.centered {
			text-align: center;
		}
		input[type="text"], input[type="email"], textarea {
			border: 1px solid #D56262;
			width: 100%;
			border-radius: 300px;
			padding: 5px;
			padding-left: 15px; /*making sure that text doesn't bleed out bc/ of border-radius*/
			background-color: #FFF7F0;
			resize: none;
			color: #D56262;
		}
		input[type="text"]:focus, input[type="email"]:focus, textarea:focus {
			outline:  none !important;
			border: 1px solid #D56262;
		}
		button {
			border: 1px solid #D56262;
			width: 85px;
			background-color: #FFF7F0;
			border-radius: 300px;
			padding: 3px;
		}
		input[type="radio"] {
	      height: 9px;
	      width: 9px;
	      background-color: #E8E8E8;
	      position: relative;
	      outline: none;
	      transform: rotate(45deg);
	      -webkit-appearance: none;
	    }
	    input[type="radio"]:before {
	      position: absolute;
	      content: "";
	      background-color: #E8E8E8;
	      height: 60%;
	      width:  100%;
	      top: -59%;
	      border-radius: 75px 75px 0 0 ;
	    }
	    input[type="radio"]:after {
	      position: absolute;
	      content: "";
	      background-color: #E8E8E8;
	      height: 60%;
	      width: 100%;
	      transform: rotate(-90deg);
	      border-radius: 75px 75px 0 0;
	      top: 20%;
	      right: 79%;
	    }
	    input[type="radio"]:checked, input:checked[type="radio"]:before, 
	    input:checked[type="radio"]:after {
	      background-color: #D56262;
	    }
	</style>
</head>
<body>
	<div id="submit_post" class="overlay">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><i class="fa-solid fa-x fa-2x" style="color: #D56262; float: right; margin: 20px"></i></a>
		<div class="overlay-content">
			<div class="new_post_header">
				<h1>New Post</h1>
			</div>
			<form action="Server" method="GET">
				<div class="submit_post_row">
					<textarea id="post_comment" placeholder="Write" name="post_comment" required></textarea>
				</div>
				<div class="submit_post_row">
					<input type="text" id="hashtag_submit_post" name="hashtag" placeholder="#CSCI201 #SAL">
				</div>
				<div class="submit_post_row" style="text-align:right">
					<button type="submit" id="submit_post_button">Submit</button>
				</div>
			</form>
		</div>
	</div>
	<!-- END OF OVERLAY --> 

	<div id="page_body">
		<div id="leftSidebar">
			<!-- <div class="column"> -->
		        <a href="homepage.jsp"><img src = "images/logo.png"></a>
		        <div class="link"><a href="homepage.jsp">Home</a></div>
		        <div class="link" id ="signin">Account Login</div>
		        <div class="link-current"><a href="contact_form.jsp">Contact Us</a></div>
		        <div class="submitPost" onclick="openNav()">Submit Post</div>
		    <!-- </div> -->
		</div>
		<!-- different div for left column/sidebar -->
		
		<div id="main_col">
			<!-- <div class="column" style="float:right; width: 100%"> -->
				<div id="contact_us" style="margin-top: 50px; line-height: 50px">
					<h1>
						Contact Us
					</h1>
				</div>
				<form action="Server" method="GET">
					<div class="row">
						<label for="name">Name:</label>
					</div>
					<div class="row" style="margin-bottom: 15px">
						<input type="text" name="name" required>
					</div>
					<div class="row">
						<label for="email">Email:</label>
					</div>
					<div class="row" style="margin-bottom: 15px">
						<input type="email" name="email" required>
					</div>
					<div class="row">
						<label>Reason for contacting:</label>
					</div>
					<div class="row" style="margin-bottom: 15px">
						<div class="selection">
							<input type="radio" value="bug" name="reason" required>
							<!-- <span class="pink fa fa-regular fa-heart"></span> -->
							<!-- <span class="icon"></span> -->
							<label for="bug">Bug fix</label>

						</div>
						<div class="selection">
							<input type="radio" value="inappropriate" name="reason">
							<!-- <span class="pink fa fa-regular fa-heart"></span> -->
							<label for="inappropriate">Inappropriate post</label>
						</div>
						<div class="selection">
							<input type="radio" value="suggestions" name="reason">
							<!-- <span class="pink fa fa-regular fa-heart"></span> -->
							<label for="suggestions"> Suggestions </label>
						</div>
						<div class="selection">
							<input type="radio" value="other" name="reason">
							<label for="other">
								Other
							</label>
						</div>
					</div>

					<div class="row">
						<label for="comment">Message:</label>
					</div>
					<div class="row">
						<textarea id="comment" name="comment" style="padding-bottom: 80px; border-radius: 30px; padding-left:20px; padding-right:20px; padding-top: 10px; margin-bottom: 10px"></textarea>
					</div>
					<div class="centered" >
						<button type="submit">Submit</button>
					</div>
				</form>
			<!-- </div> -->
		</div>
	</div>

	<script>
		function openNav() {
		  document.getElementById("submit_post").style.width = "100%";
		}

		function closeNav() {
		  document.getElementById("submit_post").style.width = "0%";
		}
	</script>
</body>
</html>