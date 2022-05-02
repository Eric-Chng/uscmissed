

import java.util.ArrayList;

public class Location {
	public Location(String address1, String address2, String address3, String city, String zip_code, String country,
			String state, ArrayList<String> display_address) {
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.zip_code = zip_code;
		this.country = country;
		this.state = state;
		this.display_address = display_address;
	}
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String zip_code;
	private String country;
	private String state;
	private ArrayList<String> display_address;
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getAddress3() {
		return address3;
	}
	public String getCity() {
		return city;
	}
	public String getZip_code() {
		return zip_code;
	}
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public ArrayList<String> getDisplay_address() {
		return display_address;
	}
	public String getDisplay_address_string() {
		String res = "";
		for (String x : display_address) {
			res += x + "\n";
		}
		return res.trim();
	}
	
	

}
