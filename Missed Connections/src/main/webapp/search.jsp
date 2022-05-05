<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  		<h1 id="results-header">Results for <%= request.getAttribute("search")%></h1>
  		<div id="results-container">
			<c:forEach var="restaurant" items="${data}">
				<div class="result">
					<a>
						<img src="${restaurant.image_url}" id="yelp-image">
					</a>
					<div>
						<h2>
						<a href="details?id=${restaurant.id}">${restaurant.name}</a>
						</h2>
						
						<p>Price: <c:out value = "${restaurant.price}"/><p>
						<p>Review Count: <c:out value = "${restaurant.RC}"/><p> 
						<p>Rating: 
						<c:forEach begin="1" end="${restaurant.rating}" varStatus="loop">&#9733; </c:forEach> 
						</p>
						<a href="${restaurant.url}" target="_blank" class="yelp-link" id="underline">Yelp Link</a>
					</div>
				</div>
			</c:forEach>
		</div>
  	</main>
  </body>
</html>