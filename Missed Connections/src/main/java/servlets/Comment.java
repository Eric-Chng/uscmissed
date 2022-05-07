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
		int user_id = -1;
		Cookie cookies[] = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("userid")) {
//				user_id = db.get_user_data(Integer.parseInt(c.getValue()));
				user_id = Integer.parseInt(c.getValue().trim());
			}
		}
		
		String error=null;
		int id = Integer.parseInt(request.getParameter("comment_post_id"));
		
		if (user_id == -1) {
			//no user logged in
			error="User is not logged in. Cannot comment.";
			request.setAttribute("error", error);
		}
		else {
			//Getting comment
			String comment = request.getParameter("comment");
			
			System.out.println(comment);
			
			//Getting current post 
			System.out.println(id);
			
			System.out.println("User");
			System.out.println(user_id);
			
			//Adding comment to database
			db.addComment(id, user_id, comment);
			
			//Updating post in request
			request.setAttribute("post", db.get_post(id, user_id));
			request.setAttribute("postid", id);
		}
		//Redirect to ViewPost servlet
		response.sendRedirect("expand.jsp" + "?postid=" + id);
	}
}

