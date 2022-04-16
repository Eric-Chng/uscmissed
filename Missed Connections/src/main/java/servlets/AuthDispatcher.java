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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Google Post");
		// User signed in with Google
		if (request.getParameter("id_token") != null) {
				
			// Create login session for user
//			System.out.println("Google");
			HttpSession session = request.getSession();
			
			
//			JacksonFactory jacksonFactory = new JacksonFactory();
//	        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier(new NetHttpTransport(),
//	                jacksonFactory);
//	        
//	        GoogleIdToken token = GoogleIdToken.parse(jacksonFactory, tokenString);
//	        

			
			System.out.println(request.getParameter("id_token"));
			session.setAttribute("name", request.getParameter("id_token"));
			
            request.getRequestDispatcher("/home.jsp").forward(request, response); 
		}
		
		// Login form submitted
		else if (request.getParameter("login") != null) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			// Server side validation
			String errorMessage = null;
			if (!validEmail(email)) {
				errorMessage = "*You must enter a valid email address.";
			} else if (password.isBlank()) {
				errorMessage = "*You must enter a password.";
			} else if (!db.validate(email, password)) {
				errorMessage = "*Invalid email and password.";
			} 
			
			if (errorMessage != null) {
				System.out.println("Not Null");
				System.out.println(errorMessage);
				request.setAttribute("loginError", errorMessage);
	            request.getRequestDispatcher("/login.jsp").forward(request, response); 
			} else {
				// Create login session for user
				HttpSession session = request.getSession();
				session.setAttribute("name", db.getName(email));
				session.setAttribute("email", email);
				System.out.println(session.getAttribute("name"));
	            request.getRequestDispatcher("/home.jsp").forward(request, response); 
			}	
		}
		
		// Registration form submitted
		else if (request.getParameter("register") != null) {
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String confirm_pass = request.getParameter("confirm_pass");
			String terms = request.getParameter("terms");
			
			// Server side validation
			String errorMessage = null;
			if (!validEmail(email)) {
				errorMessage = "*You must enter a valid email address.";
			} else if (name.isBlank()) {
				errorMessage = "*You must enter a name.";
			} else if (password.isBlank()) {
				errorMessage = "*You must enter a password.";
			} else if (!password.equals(confirm_pass)) {
				errorMessage = "*The passwords you entered do not match.";
			} else if (terms == null) {
				errorMessage = "*You must agree to the terms of service.";
			} else {
				try { 
					// Register user to database
					System.out.println("AuthServlet: Calling Database Function");
					db.registerUser(new User(email, name, password));
					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					session.setAttribute("email", email);
		            request.getRequestDispatcher("/home.jsp").forward(request, response);
		            return;
				} catch (SQLIntegrityConstraintViolationException e) {
					errorMessage = "*That email address is already in use.";
				}
			}
			
			// Registration failed, send error message
			request.setAttribute("registerError", "<p class=\"error-message\">" + errorMessage + "</p>");
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("name") != null){
            session.removeAttribute("name");
            session.removeAttribute("email");
            request.getRequestDispatcher("/home.jsp").forward(request, response); 
        }
	}
	
	private boolean validEmail(String email) {
		if (email.isBlank()) { return false; }
		Pattern zipPattern = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");
	    return zipPattern.matcher(email).matches();
	}
}
