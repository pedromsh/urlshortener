package com.pedromorais.urlshortener.utils;

import java.security.SecureRandom;

public class UrlShortenerRandom {
	private static final String CHAR_POOL = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final SecureRandom random = new SecureRandom();
	
	public static String generateShortCode(int lenght) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < lenght; i++) {
			int index = random.nextInt(CHAR_POOL.length());
			sb.append(CHAR_POOL.charAt(index));
		}
		
		return sb.toString();
	}
}
