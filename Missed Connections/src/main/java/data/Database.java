package data;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;


import util.User;
import util.Post;


public class Database {
	
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String ADDRESS = "jdbc:mysql://localhost:3306/uscmissed";
	private static String USER = "root";
	private static String PASSWORD = "password";
	
	public void registerUser(User user){
		String INSERT_USERS_SQL = "INSERT INTO user (email, name, type) VALUES (?, ?, ?)";
		try {
			// Connect to database
			Class.forName(DRIVER);
			System.out.println("Database: Creating User");
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Insert new user to database
			PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getStatus()); //(status -> type) for (backend -> db) mapping
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String get_user_name(int user_id) {
		String SELECT_NAME_SQL = "SELECT Name FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(SELECT_NAME_SQL);
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getString("content");
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String get_user_email(int user_id) {
		String SELECT_NAME_SQL = "SELECT email FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(SELECT_NAME_SQL);
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getString("content");
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String get_user_status(int user_id) {
		String SELECT_NAME_SQL = "SELECT type FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(SELECT_NAME_SQL);
			statement.setInt(1, user_id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getString("content");
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User get_user_data(int user_id) {
		String name = get_user_name(user_id);
		String email = get_user_email(user_id);
		String status = get_user_status(user_id);	
		User res = new User(user_id, name, email, status);
		return res;
	}
	
	
	public String get_post_content(int post_id) {
		String SELECT_NAME_SQL = "SELECT content FROM posts WHERE post_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(SELECT_NAME_SQL);
			statement.setInt(1, post_id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			result = rs.getString("content");
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> get_post_comment(int post_id) {
		String POST_COMMENT_SQL = "SELECT distinct(Comment) FROM comment WHERE post_id = ?";
		ArrayList<String> result = new ArrayList<String>();
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(POST_COMMENT_SQL);
			statement.setInt(1, post_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				result.add(rs.getString("Comment"));
			}
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int get_post_likes(int post_id) {
		String POST_LIKES_SQL = "SELECT count(post_id) AS data FROM likes WHERE post_id = ?";
		int result = 0;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(POST_LIKES_SQL);
			statement.setInt(1, post_id);
			ResultSet rs = statement.executeQuery();
			result = (rs.getInt("data"));
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int if_user_liked(int post_id, int user_id) {
		String POST_LIKES_SQL = "SELECT count(post_id) AS data FROM likes WHERE post_id = ? and user_id = ?";
		int result = 0;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(POST_LIKES_SQL);
			statement.setInt(1, post_id);
			statement.setInt(2, user_id);
			ResultSet rs = statement.executeQuery();
			result = (rs.getInt("data"));
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public Post get_post(int post_id, int user_id) {
		int id = post_id;
		String content = get_post_content(post_id);
		ArrayList<String> comments = get_post_comment(post_id);
		int likes = get_post_likes(post_id);
		boolean likedByUser;
		if (if_user_liked(post_id, user_id) == 0) {
			likedByUser = false;
		}
		else {
			likedByUser = true;
		}
		Post post = new Post(id, content, comments, likes, likedByUser);
		return post;
	}
	
	public void addPost(Post post, int user_id){
		String INSERT_USERS_SQL = "INSERT INTO toBeApproved (user_id, content) VALUES (?, ?)";
		try {
			// Connect to database
			Class.forName(DRIVER);
			System.out.println("Database: Creating User");
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Insert new user to database
			PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL);
			statement.setInt(1, user_id);
			statement.setString(2, post.getPostContent());
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	//END OF CURRENT PAGE; REST IS TEMPLATE CODE
	
	
	
	

	
	public void input_food_db(){
		Collection<Restaurant> master_data = new ArrayList<Restaurant>();
		
		String file_name = "/Users/rkuan/Desktop/SalEats-main/restaurant_data.json";
		Gson gson = new Gson();
		Business temp_bus;
		try (Reader reader = new FileReader(file_name)) {
			Map<?, ?> map = gson.fromJson(reader, Map.class);
			ArrayList<Map<?, ?>> temp_data = (ArrayList<Map<?, ?>>) map.get("businesses");
			for (Map<?, ?> entry : temp_data) {
//				System.out.println(entry);
				Restaurant res = new Restaurant();
				res.id = (String) entry.get("id");
				res.name = (String) entry.get("name");
				res.image_url = (String) entry.get("image_url");
				res.url = (String) entry.get("url");
				res.price = (String) entry.get("price");
				res.review_count = (double) entry.get("review_count");
				res.rating = (double) entry.get("rating");
				res.phone = (String) entry.get("phone");
				
				Map<?, ?> loc_data = (Map<?, ?>) entry.get("location");
				Location loc = new Location();
				loc.address1 = (String) loc_data.get("address1");
				loc.city = (String) loc_data.get("city");
				loc.zip_code = (String) loc_data.get("zip_code");
				res.location = loc.toString();
				
				ArrayList<Map<?, ?>> cat_data = (ArrayList<Map<?, ?>>) entry.get("categories");
				
				ArrayList<Category> category_data = new ArrayList<Category>();
				for (Map<?, ?> cat_entry : cat_data) {
					Category cat = new Category();
					cat.alias = (String) cat_entry.get("alias");
					cat.title = (String) cat_entry.get("title");
					category_data.add(cat);
//					System.out.println(cat.alias);
//					System.out.println(cat.title);
				}
				res.categories = category_data;
				
//				System.out.println(res.id);
//				System.out.println(res.name);
//				System.out.println(res.image_url);
//				System.out.println(res.url);
//				System.out.println(res.price);
//				System.out.println(res.review_count);
//				System.out.println(res.rating);
//				System.out.println(res.phone);
//				System.out.println(res.location);
				
				master_data.add(res);
				}
			
			for (Restaurant entry : master_data) {
				//traverse sql database
				String INSERT_USERS_SQL_1 = "REPLACE INTO sal_eats.Restaurant (restaurant_id, restaurant_name, image_url, address, phone_no, estimated_price, yelp_url, category_name, review_count, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				String INSERT_USERS_SQL_2 = "REPLACE INTO sal_eats.Category (category_id, category_name,restaurant_id) VALUES (?, ?, ?)";
				int result = 0;
//				System.out.println(entry.id);
//				System.out.println(entry.name);
//				System.out.println(entry.image_url);
//				System.out.println(entry.location.toString());
//				System.out.println(entry.phone);
//				System.out.println(" ");
//				System.out.println(entry.url);
//				System.out.println(" ");
//				System.out.println(entry.review_count);
//				System.out.println(entry.rating);
				
				try {
					// Connect to database
					Class.forName(DRIVER);
					//System.out.println("Database: Creating Restaurant");
					Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
					
					// Insert new user to database
					PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL_1);
					statement.setString(1, entry.id);
					statement.setString(2, entry.name);
					statement.setString(3, entry.image_url);
					statement.setString(4, entry.location); //location to string?
					statement.setString(5, entry.phone);
					statement.setString(6, entry.price); //estimated price?
					statement.setString(7, entry.url);
					statement.setString(8, " "); //category name?
					statement.setDouble(9, entry.review_count); //convert to double
					statement.setDouble(10, entry.rating); //convert to double
					
					result = statement.executeUpdate();
					conn.close();
				} catch (SQLException | ClassNotFoundException e) {
					if (e instanceof SQLIntegrityConstraintViolationException) {
						e.printStackTrace();
					}
				}
				
				for (Category iterate: entry.categories) {
					String alias = iterate.alias;
					try {
						// Connect to database
						Class.forName(DRIVER);
						Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
						
						// Insert new user to database
						//System.out.println("Database: Creating Category Link");
						PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL_2);
						statement.setString(1, " ");
						statement.setString(2, alias);
						statement.setString(3, entry.id);		
						result = statement.executeUpdate();
						conn.close();
					} catch (SQLException | ClassNotFoundException e) {
						if (e instanceof SQLIntegrityConstraintViolationException) {
							e.printStackTrace();
						}
					}
					
				}
				
			//end of function	
			}
			
		}
		catch (IOException a){
			System.out.println(a + "\nFile not found!");
		}
		catch (JsonParseException b) {
			System.out.println(b + "\nJson file has syntax errors!");
		}
	}
	
	public List<Restaurant> searchRestaurants(String search_param, String category, String sort){
		List<Restaurant> res = new ArrayList<Restaurant>();
		if (category.equals("rest_name")) {
//			System.out.println("Current: rest_name");
			try {
				// Connect to database
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
				String SELECT_STATEMENT_1 = "";
				// Grab queried name
				if (sort.equals("price")) {
//					System.out.println("type: price");
					SELECT_STATEMENT_1 = "SELECT * FROM sal_eats.Restaurant WHERE Restaurant.restaurant_name LIKE ? and estimated_price is not null ORDER BY estimated_price ASC";
				}
				else if (sort.equals("review_count")){
					SELECT_STATEMENT_1 = "SELECT * FROM sal_eats.Restaurant WHERE Restaurant.restaurant_name LIKE ? and review_count is not null ORDER BY (review_count) * 1 DESC";						
				}
				else{
					SELECT_STATEMENT_1 = "SELECT * FROM sal_eats.Restaurant WHERE Restaurant.restaurant_name LIKE ? and rating is not null ORDER BY rating DESC";						
				}
				
//				System.out.println(SELECT_STATEMENT_1);
				PreparedStatement statement = conn.prepareStatement(SELECT_STATEMENT_1);
				statement.setString(1, "%"+search_param+"%");
				System.out.println(statement);
	
				ResultSet rs = statement.executeQuery();
				
				int x = 0;
				while (rs.next()) {
					x ++;
					Restaurant temp = new Restaurant();
					temp.id = rs.getString("restaurant_id");
					temp.name = rs.getString("restaurant_name");
					temp.price = rs.getString("estimated_price");
					temp.rating = rs.getDouble("rating");
					temp.review_count = rs.getDouble("review_count");
					temp.url = rs.getString("yelp_url");
					temp.image_url = rs.getString("image_url");
					temp.location = rs.getString("address");
					temp.phone = rs.getString("phone_no");
					
//					System.out.println(temp.price);
					res.add(temp);
				}
//				System.out.println(x);
				conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if (category.equals("category")) {
//			System.out.println("Current: category");
			try {
				// Connect to database
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
				String SELECT_STATEMENT_1 = "SELECT distinct(Restaurant.restaurant_id), Restaurant.restaurant_name, Restaurant.image_url, Restaurant.address, Restaurant.phone_no, Restaurant.estimated_price, Restaurant.yelp_url, Restaurant.review_count, Restaurant.rating ";
				String A = "FROM sal_eats.Category ";
				String B = "LEFT JOIN sal_eats.Restaurant ON Category.restaurant_id = Restaurant.restaurant_id ";
				String C = "";
				String D = "";
					
				// Grab queried name
				if (sort.equals("price")) {
					C = "WHERE Category.category_name LIKE ? and Category.category_name is not null and estimated_price is not null ";
					D = "ORDER BY Restaurant.estimated_price ASC";
				}
				else if (sort.equals("review_count")){
					C = "WHERE Category.category_name LIKE ? and Category.category_name is not null and review_count is not null ";
					D = "ORDER BY (Restaurant.review_count) * 1 DESC";
					
				}
				else{
					C = "WHERE Category.category_name LIKE ? and Category.category_name is not null and rating is not null ";
					D = "ORDER BY Restaurant.rating DESC";
					
				}
				String SQL_Statement = SELECT_STATEMENT_1 + A + B + C + D;
				PreparedStatement statement = conn.prepareStatement(SQL_Statement);
				statement.setString(1, "%"+search_param+"%");
				System.out.println(statement);
	
				ResultSet rs = statement.executeQuery();
				
				int x = 0;
				while (rs.next()) {
					x ++;
					Restaurant temp = new Restaurant();
					temp.id = rs.getString("restaurant_id");
					temp.name = rs.getString("restaurant_name");
					temp.price = rs.getString("estimated_price");
					temp.rating = rs.getDouble("rating");
					temp.review_count = rs.getDouble("review_count");
					temp.url = rs.getString("yelp_url");
					temp.image_url = rs.getString("image_url");
					temp.location = rs.getString("address");
					temp.phone = rs.getString("phone_no");
					
//					System.out.println(temp.name);
					res.add(temp);
				}
//				System.out.println(x);
				conn.close();
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public Restaurant find_restaurant_given_ID(String id){
		Restaurant res = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String SELECT_STATEMENT_1 = "SELECT * from sal_eats.Restaurant where Restaurant.restaurant_id = ? ";
			PreparedStatement statement = conn.prepareStatement(SELECT_STATEMENT_1);
			statement.setString(1, id);
			System.out.println(statement);

			ResultSet rs = statement.executeQuery();
			Restaurant temp = new Restaurant();
			
			while (rs.next()) {
				temp.id = rs.getString("restaurant_id");
				temp.name = rs.getString("restaurant_name");
				temp.price = rs.getString("estimated_price");
				temp.rating = rs.getDouble("rating");
				temp.review_count = rs.getDouble("review_count");
				temp.url = rs.getString("yelp_url");
				temp.image_url = rs.getString("image_url");
				temp.location = rs.getString("address");
				temp.phone = rs.getString("phone_no");
				
				System.out.println(temp.id);
				System.out.println(temp.name);
				
			}
			
//			System.out.println(x);
			conn.close();
			Database db = new Database();
			String categories = db.get_categories(id);
			
			temp.categories_display = categories;
			System.out.println(temp.categories_display);
			return temp;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public String get_categories(String id){
		String res = "";
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String SELECT_STATEMENT_1 = "SELECT distinct(category_name) from sal_eats.Category where Category.restaurant_id = ? ";
			PreparedStatement statement = conn.prepareStatement(SELECT_STATEMENT_1);
			statement.setString(1, id);
			System.out.println(statement);

			ResultSet rs = statement.executeQuery();
			boolean first = true;
			
			while (rs.next()) {
				String S = rs.getString("category_name");
				if (first) {
					first = false;
					res = res.concat(S);
				}
				else {
					String temp = ", ";
					String curr = temp.concat(S); 
					res = res.concat(curr);
					
				}
//				System.out.println(res);
			}
			conn.close();
			return res;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	
	}
}
	
