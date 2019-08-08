package org.fkjava.dto.support;

public class StringUtils {
	
	public static String transfer(String str){
		String prefix = str.substring(0, 1).toUpperCase();
		return prefix + str.substring(1);
	}
}
