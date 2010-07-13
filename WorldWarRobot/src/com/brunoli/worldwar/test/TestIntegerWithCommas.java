package com.brunoli.worldwar.test;

import java.text.DecimalFormat;

public class TestIntegerWithCommas {

	public static void main(String[] args) {
		int d = 123456;
		DecimalFormat df = new DecimalFormat("###,###");
		System.out.println(df.format(d));
	}
}
