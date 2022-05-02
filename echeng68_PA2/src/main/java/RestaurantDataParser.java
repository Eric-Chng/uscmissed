


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import echeng68_PA2_Util.BusinessMini;
import echeng68_PA2_Util.Categories;

/**
 * A class that pretends to be the Yelp API
 */
public class RestaurantDataParser {
    private static Boolean ready = false;

    /**
     * Initializes the DB with json data
     *
     * @param responseString the Yelp json string
     */
    public static void Init(String responseString) {
    	System.out.println("2 Iteration: " +responseString);
        if (ready) {
            return;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //TODO check if you've done the initialization
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ready = true;
        ArrayList<Business> blist = new ArrayList<>();
        Gson gson =
                new GsonBuilder()
                .registerTypeAdapter(Business.class, new BusinessDeserializer())
                .create();
        String filePath = Constant.FileName;
        if (responseString != null)
        	filePath = responseString;
        try ( BufferedReader file = new BufferedReader(new FileReader(filePath))) {
        	System.out.println("file read");
        	Object temp =  gson.fromJson(file, new TypeToken<Business>() {}.getType());
        	blist = (ArrayList<Business>) temp;
//        	for (Business x: blist) {
//        		System.out.println(x);
//        	}
        	//json parsing works now
        	
        }
        catch (FileNotFoundException e) {System.out.println("Cannot open file: " + e.getMessage());}
        catch (IOException e) {System.exit(0);}
        catch (JsonSyntaxException e) {System.out.println("Json Syntax Exception: " + e.getMessage());}
        catch (JsonParseException e) {System.out.println("Error: " + e.getMessage());}
        String db = "jdbc:mysql://localhost/PA2";
        String user = "root";
        String pwd = "root";
        /* Is this necessary? Might have to 
         * SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE table $table_name; 
SET FOREIGN_KEY_CHECKS = 1;
to make it work
        try (Connection conn = DriverManager.getConnection(db, user, pwd);
        	Statement st = conn.createStatement();) {
        	st.executeUpdate("TRUNCATE Category");
        	st.executeUpdate("TRUNCATE Restaurant");
        	st.executeUpdate("TRUNCATE Rating_details");
        	st.executeUpdate("TRUNCATE Restaurant_details");
		 } catch (SQLException ex) {
		 System.out.println ("SQLException: " + ex.getMessage());}
        */
        String rt_det_sql = "INSERT INTO Rating_details (rating_id, review_count, rating) VALUES (?, ?, ?);";
        String rs_det_sql = "INSERT INTO Restaurant_details (details_id, image_url, address, phone_no, estimated_price, yelp_url) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        String rs_sql = "INSERT INTO Restaurant (restaurant_id, restaurant_name, details_id, rating_id) VALUES (?, ?, ?, ?)";
        String ct_sql = "INSERT INTO Category (category_name, restaurant_id) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(db, user, pwd);
        		PreparedStatement ps = conn.prepareStatement(rt_det_sql);
        		PreparedStatement rsps = conn.prepareStatement(rs_det_sql);
        		PreparedStatement rs = conn.prepareStatement(rs_sql);
        		PreparedStatement cs = conn.prepareStatement(ct_sql);) {
        	
    		 for (int i = 0; i < blist.size(); ++i) {
    			 Business tempb = blist.get(i);
    			 ps.setInt(1, i+1);
    			 ps.setInt(2, blist.get(i).getReview_count());
    			 ps.setDouble(3, blist.get(i).getRating());
    			 ps.addBatch();
    			 rsps.setInt(1, i+1);
    			 rsps.setString(2, tempb.getImage_url());
    			 rsps.setString(3, tempb.getLocation().getDisplay_address_string());
    			 rsps.setString(4, tempb.getPhone());
    			 if (tempb.getPrice()!= null)
    			 rsps.setString(5, tempb.getPrice());
    			 else
    				 rsps.setString(5, "N/A");
    			 rsps.setString(6, tempb.getUrl());
    			 rsps.addBatch();
    			 rs.setString(1, tempb.getId());
    			 rs.setString(2, tempb.getName().replaceAll("’", "'").replaceAll("'", "%27"));
    			 rs.setInt(3, i+1);
    			 rs.setInt(4, i+1);
    			 rs.addBatch();
    			 ArrayList<Categories> tempc = tempb.getCategories();
    			 for (Categories cttemp : tempc) {
    				 cs.setString(1, cttemp.getTitle());
    				 cs.setString(2, tempb.getId());
    				 cs.addBatch();
    			 }
    			 
    		 }
    		 ps.executeBatch();
    		 rsps.executeBatch();
    		 rs.executeBatch();
    		 cs.executeBatch();
		 } catch (SQLException ex) {System.out.println ("SQLException: " + ex.getMessage());}
    	System.out.println("Done inserting Businesses into DB");
    	//correctly adds json data into DB
    }
    

    //Is this necessary
    public static BusinessMini getBusiness(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //TODO return business based on id
        String db = "jdbc:mysql://localhost/PA2";
        String sql = "SELECT * FROM Restaurant INNER JOIN Restaurant_details ON Restaurant.details_id = Restaurant_details.details_id "
        		+ "INNER JOIN Rating_details ON Restaurant.rating_id = Rating_details.rating_id "
        		+ "WHERE Restaurant.restaurant_id = '" + id + "'";
        String csql = "SELECT * FROM Category WHERE restaurant_id = '" + id + "'";
        try (Connection conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql); 
        		PreparedStatement ps = conn.prepareStatement(csql);
        		ResultSet prs = ps.executeQuery();) {
                while (rs.next()) {
                	
                	String name = rs.getString("restaurant_name");
                	String image_url = rs.getString("image_url");
                	String address = rs.getString("address");
                	String phone_no = rs.getString("phone_no");
                	String estimated_price = rs.getString("estimated_price");
                	String yelp_url = rs.getString("yelp_url");
                	int review_count = rs.getInt("review_count");
                	double rating = rs.getDouble("rating");
                	BusinessMini temp = new BusinessMini(name, image_url, address, phone_no, estimated_price, yelp_url, review_count, rating);
                	temp.id = id;
                	//handle categories
                	ArrayList<Categories> tempct = new ArrayList<>();
                	while (prs.next()) {
                		String title = prs.getString("category_name");
                		Categories tempcat = new Categories("", title);
                		tempct.add(tempcat);
                	}
                	temp.setCategories(tempct);
                	return temp;
                }
        }catch (SQLException ex) {
            System.out.println ("SQLException: " + ex.getMessage());}
        return null;
    }

    /**
     * @param keyWord    the search keyword
     * @param sort       the sort option (price, review count, rating)
     * @param searchType search in category or name
     * @return the list of business matching the criteria
     */
    public static ArrayList<BusinessMini> getBusinesses(String keyWord, String sort, String searchType) {
        ArrayList<BusinessMini> buslist = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        keyWord = keyWord.replaceAll("'", "%27");
        //TODO get list of business based on the param
        String db = "jdbc:mysql://localhost/PA2";
       String sql = "SELECT * FROM Restaurant INNER JOIN Restaurant_details ON Restaurant.details_id = Restaurant_details.details_id "
       		+ "INNER JOIN Rating_details ON Restaurant.rating_id = Rating_details.rating_id "
       		+ "INNER JOIN Category ON Category.restaurant_id = Restaurant.restaurant_id "
       		//+ "WHERE MATCH(" + searchType + ") AGAINST('" + keyWord + "') "
       		+ "WHERE " + searchType + " LIKE '%" + keyWord + "%' "
			+ "ORDER BY " + sort;
       /* Bonus feature not needed for MVP
       if (sort.equals("rating")) {
    	   sql += " DESC";
       }  */
       System.out.println(sql);
       String csql = "SELECT * FROM Category WHERE restaurant_id = ?";

       try (Connection conn = DriverManager.getConnection(db, Constant.DBUserName, Constant.DBPassword);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
    		   PreparedStatement ps = conn.prepareStatement(csql);
       		) {
        while (rs.next()) {
        	String id = rs.getString("restaurant_id");
        	String name = rs.getString("restaurant_name");
        	String image_url = rs.getString("image_url");
        	String address = rs.getString("address");
        	String phone_no = rs.getString("phone_no");
        	String estimated_price = rs.getString("estimated_price");
        	String yelp_url = rs.getString("yelp_url");
        	int review_count = rs.getInt("review_count");
        	double rating = rs.getDouble("rating");
        	BusinessMini temp = new BusinessMini(name, image_url, address, phone_no, estimated_price, yelp_url, review_count, rating);
        	temp.id = id;
        	ps.setString(1, id);
        	ResultSet prs = ps.executeQuery();
        	ArrayList<Categories> tempct = new ArrayList<>();
        	while (prs.next()) {
        		String title = prs.getString("category_name");
        		Categories tempcat = new Categories("", title);
        		tempct.add(tempcat);
        	}
        	temp.setCategories(tempct);
        	
        	if (!buslist.contains(temp)) {
        		buslist.add(temp);
        	}
        	
        	
        }
       	 
        } catch (SQLException ex) {
        System.out.println ("SQLException: " + ex.getMessage());}
        return buslist;

    }
}

//Code adapted from https://stackoverflow.com/questions/23070298/get-nested-json-object-with-gson-using-retrofit
class BusinessDeserializer implements JsonDeserializer<ArrayList<Business>> {
    @Override
    public ArrayList<Business> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
    	JsonObject content = je.getAsJsonObject();
    	JsonElement c2 = content.get("businesses");
    	JsonArray c3 = c2.getAsJsonArray();
    	
        
        return new Gson().fromJson(c3, new TypeToken<ArrayList<Business>>() {}.getType());
    }
}