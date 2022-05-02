

import java.util.regex.Pattern;

public class Constant {
    //TODO replace it with your DB credentials
    static public String DBUserName = "root";
    static public String DBPassword = "root";
    public static String FileName = "restaurant_data.json";
    
    public static String sortPrice = "estimated_price";
    public static String sortReviewCount = "review_count";
    public static String sortRating = "rating";
    public static String searchTypeName = "restaurant_Name";
    public static String searchTypeCategory = "category_name";
    

    static public Pattern namePattern = Pattern.compile("^[a-z ,.'-]+$");
    static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$");

}
