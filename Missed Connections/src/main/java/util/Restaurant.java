package util;

import java.io.Serializable;
import java.util.ArrayList;


public class Restaurant implements Serializable {

	private static final long serialVersionUID = -6431566946329033409L;

	public String id;
	public String name;
	public String image_url;
	public String url;
	public ArrayList<Category> categories;
	public double review_count;
	public String RC;
	public double rating;
	
	
	public String price;
	public String location;
	public String phone;
	public String categories_display;
	
	
	public String getId() {
		return id;
	}
	
	public String getCategories_display() {
		return categories_display;
	}
	
	
	public double getReview_Count() {
		return review_count;
	}
	
	public String getRC() {
		return RC;
	}

	
	public String getName() {
		return name;
	}
	
	public String get_RC() {
		return name;
	}

	public String getImage_url() {
		return image_url;
	}

	public String getUrl() {
		return url;
	}

	public double getRating() {
		return rating;
	}

	public String getPrice() {
		return price;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getLocation() {
		return location;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
}
