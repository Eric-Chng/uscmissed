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
	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database db = new Database();
		
		//Getting current user
		User user = null;
		Cookie cookies[] = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("user_id")) {
				user = db.get_user_data(Integer.parseInt(c.getValue()));
			}
		}
		
		//Getting current post 
		Post post = (Post) request.getAttribute("post");
		
		//Getting comment
		String comment = request.getParameter("comment-content");
		
		//Adding comment to database
		db.addComment(post.post_id, user.user_id, comment);
		
		//Updating post in request
		request.setAttribute("post", db.get_post(post.post_id, user.user_id));
		
		//Redirect to ViewPost servlet
		response.sendRedirect("expand.jsp");
		
		
	}
	}

}
