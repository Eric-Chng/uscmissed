package util;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1595109626124665225L;

	public String alias;
	public String title;
	
	public String getAlias() {
		return alias;
	}
	
	public String getTitle() {
		return title;
	}
}
