
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import echeng68_PA2_Util.BusinessMini;
import echeng68_PA2_Util.Categories;

/**
 * Servlet implementation class SearchDispatcher
 */
@WebServlet("/SearchDispatcher")
public class SearchDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public SearchDispatcher() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        // TODO get json file as stream, Initialize FakeYelpAPI by calling its initalize
        // method
        RestaurantDataParser.Init(servletContext.getRealPath(Constant.FileName));
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
        	e1.printStackTrace();
        }
        String db = "jdbc:mysql://localhost/PA2";
        String keyWord, sort, searchType;
        if (request.getParameter("searchtype").equals("Name")) {
        	searchType = "restaurant_name";
        	
        } else {
        	searchType = "category_name";
        }
        sort = request.getParameter("sort");
        keyWord = request.getParameter("searchplaceholder");
        ArrayList<BusinessMini> blist = RestaurantDataParser.getBusinesses(keyWord, sort, searchType);
        request.setAttribute("searchType", request.getParameter("searchtype"));
        request.setAttribute("searchkeyword", keyWord);
        request.setAttribute("blist", blist);
        request.getRequestDispatcher("search.jsp").include(request, response);
        

        

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}