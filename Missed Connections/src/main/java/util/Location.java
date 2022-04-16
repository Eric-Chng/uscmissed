package util;

import java.io.Serializable;

public class Location implements Serializable {

	private static final long serialVersionUID = -2155740990206958341L;

	public String address1;
	public String city;
	public String zip_code;
	
	@Override
	public String toString() {
		return address1 + ", " + city + ", " + zip_code;
	}
}
