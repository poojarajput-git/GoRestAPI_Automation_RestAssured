package com.qa.gorest.Utils;

public class StringUtils {
	
	public static String generateRandomEmailID() {

		return "api" +System.currentTimeMillis()+ "@api.com";
	}

}
