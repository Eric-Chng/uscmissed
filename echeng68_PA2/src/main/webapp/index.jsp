<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="index.css">
        <script src="https://kit.fontawesome.com/3204349982.js" crossorigin="anonymous"></script>
        <% //TODO iterate the cookie and check if user registered %>
        
        <style>
        #searchplaceholder {
        	width: 70%;
        	font-size:16px;
        }
        .sortradio {
        
        }
        form select {
        	font-size:16px;
        }
        form button {
        	background: red;
        	font-size:16px;
        	color: white;
        	padding: 6px;
        	border: red;
        	border-radius: 4px;
        	
        }
        </style>
    </head>

    <body>
    <div id="top">
        <div id="saleats">
        <p><a href="index.jsp">SalEats!</a></p>
        
        </div>
        <div id="user">
        <%
        Cookie[] cookies = request.getCookies();
        String name = null;
        if (cookies != null) {
        for (int i = 0; i <cookies.length; i++) {
        	if (cookies[i].getName().equals("name"))
        		name = cookies[i].getValue();
        }
        }
        	
        if (name == null) {
        	//nothing next to logo
        }else {
        	
        %>
        <p style="float:left; padding-left:30px;height:80px;line-height:50px; font-size:22px;"> Hi <%=name.replaceAll("="," ")%>!</p>
        <%} %>
        </div>
        <div class="links">
        <%
        if (name == null) {
        %>
        <p><a href="login.jsp">Login/Register</a></p>
        <%} else { %>
        <p><a href="http://localhost:8080/echeng68_PA2/LogoutDispatcher">Logout</a></p>
        <%} %>
        </div>
        <div class="links">
        <p><a href="index.jsp">Home</a></p>
        </div>
        </div>
        <hr style="width: 100%; float: center; border-top: gray;">
        <img src="http://localhost:8080/echeng68_PA2/banner.jpeg" alt="SalEats food banner" style="padding-top: 10px; padding-bottom: 10px; pointer-events:none;border-radius:25px; user-select:none;max-width:100%;max-height:100vh;">
        <div id="search">
        	<form action="SearchDispatcher" method="GET">
        	<select name="searchtype" id="searchtype" onchange='changePlaceholder()' required>
        		<option value="Name" selected>Name</option>
        		<option value="Category">Category</option>
        	</select>
        	<input type="text" id="searchplaceholder" placeholder="Name" name="searchplaceholder" required>
        	<button type="submit"><i class="fa fa-search"></i></button>
        	<div style="display: inline; float: right; padding-right: 2%">
        	<label>
        	<input type="radio" class="sortradio" name="sort" id="price" value="estimated_price" checked required>
        	Price
        	</label>
        	<label>
        	<input type="radio" class="sortradio" name="sort" id="review_count" value="review_count">
        	Review Count
        	</label>
        	<br>
        	<label>
        	<input type="radio" class="sortradio" name="sort" id="rating" value="rating">
        	Rating
        	</label>
        	</div>
        	</form>
        </div>
        <script>
        function changePlaceholder() {
        	var type = document.getElementById("searchtype").value;
        	document.getElementById("searchplaceholder").setAttribute("placeholder", type);
        }
        </script>
    </body>

    </html>