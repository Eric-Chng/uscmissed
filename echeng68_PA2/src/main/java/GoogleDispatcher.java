
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
 * Servlet implementation class GoogleDispatcher
 */
@WebServlet("/GoogleDispatcher")
public class GoogleDispatcher extends HttpServlet {
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
        String name = (String)request.getParameter("name");
        String email =(String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        
        String db = "jdbc:mysql://localhost/PA2";
    	String sql = "SELECT * FROM Users WHERE email = '" + email + "'";
    	String isql = "INSERT INTO Users (Name, email, password, isGoogle) VALUES (?, ?, ?, ?)"; 
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    	try (Connection conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql); 
        		PreparedStatement ps = conn.prepareStatement(isql);
        		) {
    		if(rs.next()) {
    			if (rs.getInt("isGoogle") == 0) {
    				ps.setString(1,name);
            		ps.setString(2, email);
            		ps.setString(3, password);
            		ps.setInt(4, 1);
            		ps.executeUpdate();
    			}
    		} else {
    			ps.setString(1,name);
        		ps.setString(2, email);
        		ps.setString(3, password);
        		ps.setInt(4, 1);
        		ps.executeUpdate();
    		}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        Cookie cookie = new Cookie("name", name.replaceAll(" ", "="));
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        //this doesn't work bc u have to refresh page before cookies show up
        response.sendRedirect("index.jsp");
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
