package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Database;
import util.Restaurant;


@WebServlet("/Search")
public class SearchDispatcher extends HttpServlet {

	private static final long serialVersionUID = 3901610548642762613L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search_param = request.getParameter("restaurant");
		String category = request.getParameter("column");
		String sort = request.getParameter("sort");
		
		request.getSession().setAttribute("radiovalue",request.getParameter("sort"));
		request.getSession().setAttribute("column_value",request.getParameter("column"));
		
		
//		System.out.println(search_param);
//		System.out.println(category);
//		System.out.println("DEBUG");
		
//		// Validate that all fields are filled out
		if (search_param.isBlank() | category.isBlank() | sort == null) {
			request.getRequestDispatcher("/home.jsp").forward(request, response); 
			return;
		}
		Database d = new Database();
		d.input_food_db();
		
		List<Restaurant> restaurants = d.searchRestaurants(search_param, category, sort);
		for(Restaurant obj : restaurants) {
			int x = (int) obj.review_count;
			obj.RC = String.valueOf(x);  
//			System.out.println(obj.review_count);
//			System.out.println(obj.RC);
		}
		request.setAttribute("search", search_param);
		request.setAttribute("data", restaurants);
		
//		System.out.println("EXECUTE");
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}
}
