package com.zsw.util;

/**
 * 字节转换工具类
 * @author andy
 */
public class Byte2Hex {
	
	/** 16进制数组 */
	private final static byte[] hex = "0123456789ABCDEF".getBytes();

	/**
	 * int转字节数组
	 * @param n - 数据
	 * @return 字节数组
	 */
	public static byte[] int2bytes(int n) {
		byte[] ab = new byte[4];
		ab[0] = (byte) (0xff & n);
		ab[1] = (byte) ((0xff00 & n) >> 8);
		ab[2] = (byte) ((0xff0000 & n) >> 16);
		ab[3] = (byte) ((0xff000000 & n) >> 24);
		return ab;
	}
	 
	/**
	 * short转字节数组
	 * @param  n - 数据
	 * @return 字节数组
	 */
	public static byte[] short2bytes(short n) {
		byte[] b = new byte[2];
		b[0] = (byte) ((n & 0xFF00) >> 8);
		b[1] = (byte) (n & 0xFF);
		return b;
	}
	 
	/**
	 * 字节数组转short
	 * @param b - 字节数组
	 * @return 数据
	 */
	public static short bytes2short(byte[] b) {
		short n = (short) (((b[0] < 0 ? b[0] + 256 : b[0]) << 8) + (b[1] < 0 ? b[1] + 256
				: b[1]));
		return n;
	}

	/**
	 * 字节数组转int
	 * @param b - 字节数组
	 * @return 数据
	 */
	public static int bytes2int(byte b[]) {
		int s = 0;
		s = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8
				| (b[3] & 0xff);
		return s;
	}

	/**
	 * 字节转char
	 * @param  b - 字节
	 * @return
	 */
	public static char byte2char(byte b) {
		return (char) b;
	}

	/**
	 * char转int
	 * @param c - 数据
	 * @return 数据
	 */
	public static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	/**
	 * 字节数组转16进制数
	 * @param b - 字节数组
	 * @return 16进制数
	 */
	public static String Bytes2HexString(byte[] b) {
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];
		}
		return new String(buff);
	}

	/**
	 * 16进制数转字节数组
	 * @param hexstr - 16进制数
	 * @return 字节数组
	 */
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	
	
	
}
