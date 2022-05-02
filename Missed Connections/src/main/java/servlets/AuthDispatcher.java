package servlets;


import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import data.Database;
import data.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Auth")
public class AuthDispatcher extends HttpServlet {
	private static final long serialVersionUID = 7507777331673090255L;
	private Database db;
	
	public void init() throws ServletException {
		db = new Database();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String category = request.getParameter("reason");
		String message = request.getParameter("content");
		
		System.out.println(name);
		System.out.println(email);
		System.out.println(category);
		System.out.println(message);
		
		System.out.println("working");
		
		request.getRequestDispatcher("/homepage.jsp").forward(request, response);
		
	}	
}
