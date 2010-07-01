package com.brunoli.worldwar.util;

public class UtilsWW {

	public static Long parsearMoney(String price) {
		price = price.replaceAll(",", "").replaceAll("\\.", "");
		if (price.contains("K")) {
			// k
			price = price.replace("K", "000");
		} else if (price.contains("mil")) {
			price = price.replace("mil", "000000");
		}
		return Long.parseLong(price);
	}
}
