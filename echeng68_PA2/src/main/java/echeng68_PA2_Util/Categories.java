package echeng68_PA2_Util;


public class Categories {

	private String alias;
	private String title;
	
	public Categories(String alias, String title) {
		this.alias = alias;
		this.title = title;
	}
	
	public String getAlias() {
		return alias;
	}
	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Categories [title=" + title + "]";
	}
	
	
	
}
