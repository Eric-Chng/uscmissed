
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class LoginDispatcher
 */
@WebServlet("/LoginDispatcher")
public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html"); 
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        String db = "jdbc:mysql://localhost/PA2";
    	String sql = "SELECT * FROM Users WHERE email = '" + request.getParameter("login_email") + "'";
log(sql);
        try (Connection conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql); 
        		) {
        	if (!rs.next()) {
        		request.setAttribute("loginresponse", "No account associated with this email exists");
                request.getRequestDispatcher("login.jsp").include(request,response);
        	} else {
            	String name = rs.getString("Name");
            	String email = rs.getString("email");
       		 	String password = rs.getString("password");
       		 	log("db pass: " + password);
       		 	log("form pass" + request.getParameter("login_password"));
       		 	int isGoogle = rs.getInt("isGoogle");
       		 	if (isGoogle == 1) {
       		 	request.setAttribute("loginresponse", "Email is associated with Google Auth login. Please use Google Authentication to log in");
                request.getRequestDispatcher("login.jsp").include(request,response);
        	
       		 	}
       		 	if (!password.equals(request.getParameter("login_password"))) {
       		 	request.setAttribute("loginresponse", "Incorrect Password. Please try again");
                request.getRequestDispatcher("login.jsp").include(request,response);
                return;
       		 	}
       		 Cookie cookie = new Cookie("name", name.replaceAll(" ", "="));
             cookie.setMaxAge(60*60);
             response.addCookie(cookie);
             response.sendRedirect("index.jsp");
        	}
                	
                	
                
        }catch (SQLException ex) {
            System.out.println ("SQLException: " + ex.getMessage());}
        request.setAttribute("loginresponse", "Invalid Sign In");
        request.getRequestDispatcher("login.jsp").include(request,response);

    	
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
