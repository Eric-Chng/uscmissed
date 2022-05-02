

import java.util.ArrayList;
import echeng68_PA2_Util.BusinessMini;
import echeng68_PA2_Util.Categories;

/**
 * The class used to model a business
 */
public class Business {
    
	


	public Business(String id, String alias, String name, String image_url, Boolean is_closed, String url,
			Integer review_count, Double rating, String price, String phone, String display_phone, String distance,
			Coordinates coordinates, ArrayList<String> transactions, Location location,
			ArrayList<Categories> categories) {
		this.id = id;
		this.alias = alias;
		this.name = name;
		this.image_url = image_url;
		this.is_closed = is_closed;
		this.url = url;
		this.review_count = review_count;
		this.rating = rating;
		this.price = price;
		if(this.price == null)
			this.price = "";
		this.phone = phone;
		this.display_phone = display_phone;
		this.distance = distance;
		this.coordinates = coordinates;
		this.transactions = transactions;
		this.location = location;
		this.categories = categories;
	}


	private String id;
	private String alias;
	private String name;
	private String image_url;
	private Boolean is_closed;
	private String url;
	private Integer review_count;
	private Double rating;
	private String price;
	private String phone;
	private String display_phone;
	private String distance;
	
	//need custom deserialization
	//https://stackoverflow.com/questions/32624166/how-to-get-json-array-within-json-object
	private Coordinates coordinates;
	private ArrayList<String> transactions;
	private Location location;
	//categories is wonky
	private ArrayList<Categories> categories;
	
	
	


	public String getId() {
		return id;
	}


	public String getAlias() {
		return alias;
	}


	public String getName() {
		return name;
	}


	public String getImage_url() {
		return image_url;
	}


	public Boolean getIs_closed() {
		return is_closed;
	}


	public String getUrl() {
		return url;
	}


	public Integer getReview_count() {
		return review_count;
	}


	public Double getRating() {
		return rating;
	}


	public String getPrice() {
		return price;
	}


	public String getPhone() {
		return phone;
	}


	public String getDisplay_phone() {
		return display_phone;
	}


	public String getDistance() {
		return distance;
	}




	public ArrayList<String> getTransactions() {
		return transactions;
	}




	public ArrayList<Categories> getCategories() {
		return categories;
	}


	public Coordinates getCoordinates() {
		return coordinates;
	}


	public Location getLocation() {
		return location;
	}


	@Override
	public String toString() {
		return "Business [id=" + id + ", alias=" + alias + ", name=" + name + ", image_url=" + image_url
				+ ", is_closed=" + is_closed + ", url=" + url + ", review_count=" + review_count + ", rating=" + rating
				+ ", price=" + price + ", phone=" + phone + ", display_phone=" + display_phone + ", distance="
				+ distance + ", coordinates=" + coordinates + ", transactions=" + transactions + ", location="
				+ location + ", categories=" + categories + "]";
	}

  
}

