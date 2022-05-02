
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class RegisterDispatcher
 */
@WebServlet("/RegisterDispatcher")
public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost:3306/PA4Users";

    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean error = false;
        if (!request.getParameter("user_password").equals(request.getParameter("confirm_user_password"))) {
        	error = true;
        	request.setAttribute("loginresponse", "Passwords don't match");
        	request.getRequestDispatcher("login.jsp").include(request, response);
        }
        if (!Constant.namePattern.matcher(request.getParameter("user_name")).matches()) {
        	error = true;
        	request.setAttribute("loginresponse", "Name is invalid");
        	request.getRequestDispatcher("login.jsp").include(request, response);

        }
        if (!Constant.emailPattern.matcher(request.getParameter("user_email")).matches()) {
        	error = true;
        	request.setAttribute("loginresponse", "Email is invalid");
        	request.getRequestDispatcher("login.jsp").include(request, response);
        }
        if (error)
        	return;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String db = "jdbc:mysql://localhost/PA2";
    	String sql = "SELECT * FROM Users WHERE email = '" + request.getParameter("user_email") + "'";
    	String isql = "INSERT INTO Users (Name, email, password, isGoogle) VALUES (?, ?, ?, ?)"; 

        try (Connection conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql); 
        		PreparedStatement ps = conn.prepareStatement(isql);
        		) {
        	boolean exists = false;
        	String name, email, password = "";
        	int isGoogle=0;
        	if(rs.next()) {
        		exists = true;
        		 name = rs.getString("Name");
        		 email = rs.getString("email");
        		 password = rs.getString("password");
        		 isGoogle = rs.getInt("isGoogle");
        	}
        	if (exists) {
        		if (isGoogle == 0) {
        			request.setAttribute("loginresponse", "Email is already associated with an account");
                	request.getRequestDispatcher("login.jsp").include(request, response);
                	return;
        		} 
        		
        	} else {
        		ps.setString(1,request.getParameter("user_name"));
        		ps.setString(2, request.getParameter("user_email"));
        		ps.setString(3, request.getParameter("user_password"));
        		ps.setInt(4, 0);
        		ps.executeUpdate();
        	}
        	
        	
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Cookie cookie = new Cookie("name", request.getParameter("user_name").replaceAll(" ", "="));
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        response.sendRedirect("index.jsp");
        	
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
