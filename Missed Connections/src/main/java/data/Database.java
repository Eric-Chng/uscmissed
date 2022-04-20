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
	
	
	//Account Validation Database Functions
	
	//takes user object and add the new user to the users database
	public void registerUser(User user){
		String REGISTER_USER = "INSERT INTO user (email, name, type) VALUES (?, ?, ?)";
		try {
			Class.forName(DRIVER);
			System.out.println("Database: Creating User");
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(REGISTER_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getStatus()); //(status -> type) for (backend -> db) mapping
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//takes in user_id and returns the name of the user as a String
	public String get_user_name(int user_id) {
		String GET_USER_NAME = "SELECT Name FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(GET_USER_NAME);
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
	
	//takes in user_id and returns the email of the user as a String
	public String get_user_email(int user_id) {
		String GET_USER_EMAIL = "SELECT email FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(GET_USER_EMAIL);
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
	
	//takes in user_id and returns the status of the user (admin vs user) as a String
	public String get_user_status(int user_id) {
		String GET_USER_STATUS = "SELECT type FROM user WHERE user_id = ?";
		String result = null;
		try {
			// Connect to database
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			
			// Grab queried name
			PreparedStatement statement = conn.prepareStatement(GET_USER_STATUS);
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
	
	//takes in user_id, returns a User object associated with the passed user_id
	public User get_user_data(int user_id) {
		String name = get_user_name(user_id);
		String email = get_user_email(user_id);
		String status = get_user_status(user_id);	
		User res = new User(user_id, name, email, status);
		return res;
	}
	
	
	//Posts Database Functions
	
	//takes post_id and returns the associated content (user's post) as a string
	public String get_post_content(int post_id) {
		String GET_POST_CONTENT = "SELECT content FROM posts WHERE post_id = ?";
		String result = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(GET_POST_CONTENT);
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
	
	//takes post_id and returns all the comments as an Arraylist<String>
	public ArrayList<String> get_post_comment(int post_id) {
		String GET_POST_COMMENT = "SELECT distinct(Comment) FROM comment WHERE post_id = ?";
		ArrayList<String> result = new ArrayList<String>();
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(GET_POST_COMMENT);
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
	
	//takes post_id and returns the number of likes on that post as a int
	public int get_post_likes(int post_id) {
		String GET_POST_LIKES = "SELECT count(post_id) AS data FROM likes WHERE post_id = ?";
		int result = 0;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(GET_POST_LIKES);
			statement.setInt(1, post_id);
			ResultSet rs = statement.executeQuery();
			result = (rs.getInt("data"));
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	//takes post_id, user_id and determines if the given user has liked the post (0 = NO, 1 = YES)
	public int if_user_liked(int post_id, int user_id) {
		String IF_USER_LIKED = "SELECT count(post_id) AS data FROM likes WHERE post_id = ? and user_id = ?";
		int result = 0;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(IF_USER_LIKED);
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
	
	//given post_id and user_id, will return a Post object associated with the passed post_id
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
	
	//given a Post object and user_id, add the post to the "to be approved" database
	public void addPost(Post post, int user_id){
		String ADD_POST = "INSERT INTO toBeApproved (user_id, content) VALUES (?, ?)";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(ADD_POST);
			statement.setInt(1, user_id);
			statement.setString(2, post.getPostContent());
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//Feed Database Functions
	
	//TO BE IMPLEMENTED: returns the most recent 100 post to show on the feed as Arraylist<Post>
	
	//TO BE IMPLEMENTED: takes in user_id, post_id and increments like counter for associated post (user likes a post)
	
	//TO BE IMPLEMENTED: takes in a hashtag (type String) and returns the associated posts as Arraylist<Post>
	
	
	//Commenting Database Functions
	
	//takes post_id, user_id, and string and adds them as an entry to the comment database
	public void addComment(int post_id, int user_id, String comment){
		String ADD_COMMENT = "INSERT INTO comment (user_id, post_id, Comment) VALUES (?, ?, ?)";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(ADD_COMMENT);
			statement.setInt(1, user_id);
			statement.setInt(2, post_id);
			statement.setString(3, comment);
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Feedback Database Functions
	
	//takes category (String), feedback data (String), and email (String) and adds to feedback database
	public void addComment(String category, String feedback_data, String email){
		String ADD_FEEDBACK = "INSERT INTO feedback (category, feedback_data, email) VALUES (?, ?, ?)";
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			PreparedStatement statement = conn.prepareStatement(ADD_FEEDBACK);
			statement.setString(1, category);
			statement.setString(2, feedback_data);
			statement.setString(3, email);
			ResultSet rs = statement.executeQuery();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Admin Approval Database Functions
	
	//TO BE IMPLEMENTED: approve function
	
	//TO BE IMPLEMENTED: reject function
	
	
}
	
