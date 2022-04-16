<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  			<span id="buttons">
	  			<a href="home.jsp" class="no-style" id="home">Home</a>
	  			<%
	  				if (null != request.getSession().getAttribute("name")) {
			        	out.println("<a href=\"favorites\" class=\"no-style\" id=\"favorites\">Favorites</a>");
			        	out.println("<a href=\"auth\" class=\"no-style\">Logout</a>");
			   		} else {
			        	out.println("<a href=\"login.jsp\" class=\"no-style\">Login / Register</a>");
			    	}
			  	%>
  			</span>
  		</nav>
  	</header>
  	<main>
  		<div>
	  		<form action="Search" method="GET">
	  			<div class="form-container">
	  			
	  				<select name="column">
						<option value="rest_name" <c:if test="${column_value eq 'rest_name'}">selected</c:if>>Restaurant Name</option>
						<option value="category" <c:if test="${column_value eq 'category'}">selected</c:if>>Category</option>
					</select><br>
					
	  				<div class="column">
				  		<input type="text" value= <%= request.getAttribute("search")%> name="restaurant" class="textbox" required><br/>
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
					              <input type="radio" value= "price" name="sort" <c:if test="${radiovalue eq 'price'}">checked</c:if> />
					              Price
					            </label>
					            <br/>
					            <label class="radio-element">
					              <input type="radio" value="rating" name="sort" <c:if test="${radiovalue eq 'rating'}">checked</c:if> />
					              Rating
					            </label>
					        </div>
				            <div>
					            <label class="radio-element">
					              <input type="radio" value="review_count" name="sort" <c:if test="${radiovalue eq 'review_count'}">checked</c:if> />
					              Review Count
					            </label>
					            <br/>
				            </div>
						</div>
					</div>
				</div>
	  		</form>
  		</div>
  		<h1 id="results-header"><%= request.getAttribute("search")%></h1>
  		<div id="results-container">
  			<div class="result" id="detail">
				<a href="${restaurant.url}" target="_blank">
					<img src="${restaurant.image_url}" alt="Image of ${restaurant.name}" id="yelp-image">
				</a>
				<div class="result-info">
					<p>Address: ${restaurant.location}</p>
					<p>${restaurant.phone}</p>
					<p>Categories: ${restaurant.categories_display}</p>
					<p>Price: ${restaurant.price}</p>
					<p>Rating: 
						<c:forEach begin="1" end="${restaurant.rating}" varStatus="loop">&#9733; </c:forEach> 
					</p>
				</div>
			</div>
		</div>
  	</main>
  </body>
</html>