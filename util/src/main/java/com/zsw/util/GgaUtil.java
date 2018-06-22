package com.zsw.util;

public class GgaUtil {

	public static String getCheck(String msg) {
		int result;

		int i = 2;
		for (result = (0xff & msg.charAt(1)); (msg.charAt(i) & 0xff) != '*'; i++) {
			result ^= (0xff & msg.charAt(i));
		}
		String s = Integer.toHexString(result);
//		System.out.println("s:  " + s);

		return s.toUpperCase();
	}

	public static String createGga(String msg) {
		return msg + getCheck(msg);
	}

}
