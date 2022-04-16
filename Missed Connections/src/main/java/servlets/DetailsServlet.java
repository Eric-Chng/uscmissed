package servlets;

import java.io.IOException;

import data.Database;
import util.Restaurant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/details")
public class DetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 9089740197565786040L;
	private Database database;
	
	public void init() throws ServletException {
		database = new Database();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Detail_Servlet");
		String id = request.getParameter("id");
		System.out.println(id);
		
		if (id == null || id.isBlank()) {
			request.getRequestDispatcher("/home.jsp").forward(request, response); 
			return;
		}
		
		Restaurant restaurant = database.find_restaurant_given_ID(id);
		System.out.println("A "+restaurant.categories_display);
		if (restaurant == null) {
			request.getRequestDispatcher("/home.jsp").forward(request, response); 
			return;
		}
		
		request.setAttribute("search", restaurant.getName());	
		request.setAttribute("restaurant", restaurant);
		request.getRequestDispatcher("/details.jsp").forward(request, response);
	}
}
