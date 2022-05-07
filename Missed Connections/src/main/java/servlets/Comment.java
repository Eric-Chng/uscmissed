package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Post;
import util.User;
import data.Database;

@WebServlet("/Comment")
public class Comment extends HttpServlet {
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
		
		
		//Getting comment
		String comment = request.getParameter("comment");
		
		System.out.println(comment);
		
		//Getting current post 
		int id = Integer.parseInt(request.getParameter("comment_post_id"));
		System.out.println(id);
		
		System.out.println("User");
		System.out.println(user);
		
		//Adding comment to database
		db.addComment(id, user.user_id, comment);
		
		//Updating post in request
		request.setAttribute("post", db.get_post(id, user.user_id));
		request.setAttribute("postid", id);
//		
		//Redirect to ViewPost servlet
		response.sendRedirect("expand.jsp" + "?postid=" + id);
		
		
	}
}

