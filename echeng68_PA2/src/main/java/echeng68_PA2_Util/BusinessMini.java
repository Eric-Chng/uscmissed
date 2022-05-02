package echeng68_PA2_Util;


import java.util.ArrayList;

public class BusinessMini {
	public BusinessMini(String name, String image_url, String address, String phone_no, String estimated_price,
			String yelp_url, int review_count, double rating) {
		this.name = name;
		this.image_url = image_url;
		this.address = address;
		this.phone_no = phone_no;
		this.estimated_price = estimated_price;
		this.yelp_url = yelp_url;
		this.review_count = review_count;
		this.rating = rating;
		
	}
	public String id;
	private String name;
	private String image_url;
	private String address;
	private String phone_no;
	private String estimated_price;
	private String yelp_url;
	private int review_count;
	private double rating;
	private ArrayList<Categories> categories;
	public String getName() {
		return name;
	}
	public String getImage_url() {
		return image_url;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public String getEstimated_price() {
		return estimated_price;
	}
	public String getYelp_url() {
		return yelp_url;
	}
	public int getReview_count() {
		return review_count;
	}
	public double getRating() {
		return rating;
	}
	public ArrayList<Categories> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<Categories> c) {
		categories = c;
	}
	@Override
	public String toString() {
		return "BusinessMini [id=" + id + ", name=" + name + ", image_url=" + image_url + ", address=" + address
				+ ", phone_no=" + phone_no + ", estimated_price=" + estimated_price + ", yelp_url=" + yelp_url
				+ ", review_count=" + review_count + ", rating=" + rating + ", categories=" + categories + "]\n";
	}
	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
        if (!(o instanceof BusinessMini)) {
            return false;
        }
        BusinessMini c = (BusinessMini)o;
		if (c.id.equals(this.id))
			return true;
		return false;
	}
}
