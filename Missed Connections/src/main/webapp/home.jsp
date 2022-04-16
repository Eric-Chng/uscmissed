<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://kit.fontawesome.com/4b6d728af0.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <title>SalEats</title>
  </head>
  <body>
  	<header>
  		<nav>
  			<a href="home.jsp" class="no-style" id="logo">SalEats!</a>
  				<%
  				if (null != request.getSession().getAttribute("name")) {
  					String name= (String)session.getAttribute("name"); 
  					out.print("   Hi "+name+"!"); 
		   		} else {
		   			out.println("<a> </a>");
		    	}
			  	%>
  			<span id="buttons">
	  			<a href="home.jsp" class="no-style" id="home">Home</a>
	  			<%
	  				if (null != request.getSession().getAttribute("name")) {
			        	out.println("<a href=\"Auth\" onclick = \"myFunction()\" class=\"no-style\">Logout</a>");
			   		} else {
			        	out.println("<a href=\"login.jsp\" onclick = \"myFunction()\" class=\"no-style\">Login / Register</a>");
			    	}
			  	%>
  			</span>
  			 <script>
			      function myFunction() {
				      gapi.auth2.getAuthInstance().disconnect();
				      location.reload();
			   }
			 </script>
  		</nav>
  	</header>
  	<main>
  		<img id="banner" src="images/banner.jpg">
  		<div>
	  		<form action="Search" method="GET">
	  			<div class="form-container">
	  				<select name="column">
						<option value="rest_name">Restaurant Name</option>
						<option value="category">Category</option>
					</select><br>
	  				<div class="column">
				  		<input type="text" placeholder="Search Detail" name="restaurant" class="textbox" required><br/>
			  		</div>
			  		<div class="column">
			  			<div>
				  			<button type="submit" id="search_button">
							    <i class="fas fa-search"></i>
							</button>
						</div>
						<div class="radio-buttons-container">
							<div>
					            <label class="radio-element">
					              <input type="radio" value="price" name="sort" checked="checked"/>
					              Price
					            </label>
					            <br/>
					            <label class="radio-element">
					              <input type="radio" value="rating" name="sort" />
					              Rating
					            </label>
					        </div>
				            <div>
					            <label class="radio-element">
					              <input type="radio" value="review_count" name="sort" />
					              Review Count
					            </label>
					            <br/>
				            </div>
						</div>
					</div>
				</div>
	  		</form>
  		</div>
  	</main>
  </body>
</html>