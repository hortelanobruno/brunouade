package com.brunoli.worldwar.util;

import java.text.DecimalFormat;

public class UtilsWW {
	
	public static void main(String[] args){
		System.out.println(parsearMoney("5,950K"));
	}

	public static Long parsearMoney(String price) {
		String aux = "";
		if(price.contains(",")){
			for(String a : price.split(",")){
				if (a.contains("K")) {
					// k
					String b = a.replace("K", "000");
					aux += b;
				} else if (a.contains("mil")) {
					String b = a.replace("mil", "000000");
					while(b.length()>6){
						b = b.substring(0, b.length()-1);
					}
					aux += b;
				}else{
					aux += a;
				}
			}
		}else if(price.contains(".")){
			for(String a : price.split("\\.")){
				if (a.contains("K")) {
					// k
					String b = a.replace("K", "000");
					aux += b;
				} else if (a.contains("mil")) {
					String b = a.replace("mil", "000000");
					while(b.length()>6){
						b = b.substring(0, b.length()-1);
					}
					aux += b;
				}else{
					aux += a;
				}
			}
		}else{
			if (price.contains("K")) {
				// k
				String b = price.replace("K", "000");
				aux += b;
			} else if (price.contains("mil")) {
				String b = price.replace("mil", "000000");
				aux += b;
			}else {
				aux = price;
			}
		}
//		price = price.replaceAll(",", "").replaceAll("\\.", "");
//		if (price.contains("K")) {
//			// k
//			price = price.replace("K", "000");
//		} else if (price.contains("mil")) {
//			price = price.replace("mil", "000000");
//		}
		return Long.parseLong(aux);
	}
	
	public static String toMoney(Long money) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(money);
	}

}
