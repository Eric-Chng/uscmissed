package servlets;

import util.Post;

import util.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import data.Database;

@WebServlet("/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 7507777331673090255L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database db = new Database();

		//Getting current user
		User user = null;
		Cookie cookies[] = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("userid")) {
				user = db.get_user_data(Integer.parseInt(c.getValue()));
			}
		}
		
		//Getting current post; 
		int id = Integer.parseInt(request.getParameter("post_id"));
		
		Post post = db.get_post(id, user.user_id);
		
		
		//Check if unliked, then adds like to database table
		if (db.if_user_liked(post.post_id, user.user_id) == 0) {
			db.user_liked_post(post.post_id, user.user_id);
		}
		
		//Updating post in request
		request.setAttribute("post", db.get_post(post.post_id, user.user_id));
				
		//Redirect to ViewPost servlet
		response.sendRedirect("expand.jsp" + "?postid=" + post.getPostID());
		
		
	}

}
