<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
         <%@page import="java.util.*" %>
         <%@page import="echeng68_PA2_Util.BusinessMini" %>
         <%@page import="echeng68_PA2_Util.Categories" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="index.css">
    <script src="https://kit.fontawesome.com/3204349982.js"
            crossorigin="anonymous"></script>

    <%
        //TODO perform search

        //TODO check if logged in
    %>
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
        .icon {
        float: left;
        height: 175px;
        width: 175px;
        margin-right:20px;
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
        
        <div id="results">
        <p id="search_query" style="font-size:30px"><%=request.getParameter("name") %></p>
        <hr style="width: 100%; float: center; border-top: gray;">
        
        
        <% 
        String categories = request.getParameter("categories");
        log(categories);
        String ctres = "";
        if (categories != null) {
        	categories = categories.trim();
        	if(categories.equals("")) {
            	ctres = "none";
            } else {
            	ctres = categories;
            }
        } else {
        	ctres = "none";
        }
        
        		out.println("<div class='business'>");
        		out.println("<div class='icon'>");
        		out.println("<img src='"+ request.getParameter("imageurl") + "'alt='" + request.getParameter("name") +"' style='width:100%;height:100%;object-fit:cover; border-radius:20px; padding:10px;float:left;display:inline-block;'>");
        		out.println("</div> <div class='details' style='padding-top:3px'> <p>Address: " + request.getParameter("address") + "</p>");
        		out.println("<p>Phone: " + request.getParameter("phone") + "</p>");
        		out.println("<p>Categories: " + ctres.replaceAll("%26", "&") + "</p>");
        		out.println("<p>Price: " + request.getParameter("price") + "</p>");
        		out.print("<p>Rating: ");
        		double stars = Double.parseDouble(request.getParameter("rating"));
        		for (int i = 0; i < (int)stars; i++) {
        			out.print("<span class='fa fa-star'></span>");
        		}
        		if (stars - (int)stars >0)
        			out.print("<span class='fa fa-star-half'></span>");
        		out.println("</p>");
        		out.println("</div>");
        		out.println("</div>");
        		
        	
        
        %>

        
        
        </div>
        
        
        <script>
        function changePlaceholder() {
        	var type = document.getElementById("searchtype").value;
        	document.getElementById("searchplaceholder").setAttribute("placeholder", type);
        }
        </script>
        
</body>
</html>