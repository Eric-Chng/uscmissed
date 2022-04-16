package util;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import util.Restaurant;

public class Business implements Serializable {

	private static final long serialVersionUID = 1595109626124665225L;

	public Map<String, Restaurant> businesses = new HashMap();
}
