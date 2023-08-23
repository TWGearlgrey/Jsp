package kr.farmstory1.db;

import java.text.DecimalFormat;

public class Utils {

	public static String comma(String number) {
		int parsedNumber = Integer.parseInt(number);
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(parsedNumber);
	}
	
	public static String comma(int number) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(number);
	}
}