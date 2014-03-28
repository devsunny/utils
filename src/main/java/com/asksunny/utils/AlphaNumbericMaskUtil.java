package com.asksunny.utils;

public class AlphaNumbericMaskUtil {

	public static int maskDigit(int number) {

		if (number > 999999999)
			throw new IllegalArgumentException(
					"Exceed maximum allowed value exception, please use java.lang.long data type");
		String res = maskDigit(Long.toString(number));
		return Integer.valueOf(res).intValue();
	}

	public static double maskDigit(double number) {

		String res = maskDigit(Double.toString(number));
		return Double.valueOf(res).doubleValue();
	}

	public static long maskDigit(Long number) {

		String res = maskDigit(Long.toString(number));
		return Long.valueOf(res).longValue();
	}

	public static int unmaskDigit(int masked) {

		String intStr = unmaskDigit(Integer.toString(masked));
		return Integer.valueOf(intStr);
	}

	public static double unmaskDigit(double masked) {
		String intStr = unmaskDigit(Double.toString(masked));
		return Double.valueOf(intStr);
	}

	public static long unmaskDigit(long masked) {

		String intStr = unmaskDigit(Long.toString(masked));
		return Long.valueOf(intStr);
	}

	public static String maskDigit(String numberstr) {
		if (numberstr == null)
			return null;
		StringBuffer numstr = new StringBuffer(numberstr);
		for (int i = 0; i < numstr.length(); i++) {
			char d = numstr.charAt(i);
			int x = d - 48;
			if (d < 48 || d > 57) {
				;
			} else {
				int pi = (i * i) % AsciiCodec.NUMERIC_MASKS.length;
				int[] t = AsciiCodec.NUMERIC_MASKS[pi];
				numstr.setCharAt(i, (char) (t[x] + 48));
			}
		}
		return numstr.toString();
	}

	public static String unmaskDigit(String masked) {
		if (masked == null)
			return null;
		StringBuffer numstr = new StringBuffer(masked);
		for (int i = 0; i < numstr.length(); i++) {
			char d = numstr.charAt(i);
			int x = d - 48;
			if (d < 48 || d > 57) {
				;
			} else {
				int pi = (i * i) % AsciiCodec.NUMERIC_MASKS.length;
				int[] t = AsciiCodec.NUMERIC_MASKS[pi];
				int j = 0;
				for (j = 0; j < t.length; j++) {
					if (t[j] == x)
						break;
				}
				numstr.setCharAt(i, (char) (j + 48));
			}
		}
		return numstr.toString();
	}

	public static String maskAscii(String text) {
		if (text == null)
			return null;
		StringBuffer numstr = new StringBuffer(text);
		for (int i = 0; i < numstr.length(); i++) {
			char d = numstr.charAt(i);
			if (d < 256) {
				int pi = (i * i) % AsciiCodec.EXT_ASCII_TABLES.length;
				numstr.setCharAt(i, ((char) AsciiCodec.EXT_ASCII_TABLES[pi][d]));
			}
		}
		return numstr.toString();
	}

	public static String unmaskAscii(String text) {
		if (text == null)
			return null;
		StringBuffer numstr = new StringBuffer(text);
		for (int i = 0; i < numstr.length(); i++) {
			int d = numstr.charAt(i);
			if (d < 256) {
				int pi = (i * i) % AsciiCodec.EXT_ASCII_TABLES.length;
				int x = AsciiReverseCodec.ASCII_LOOKUP_HASHS.get(pi).get(
						Integer.valueOf(d));
				numstr.setCharAt(i, ((char) x));
			}
		}
		return numstr.toString();
	}

	public AlphaNumbericMaskUtil() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String masked = maskAscii("Sunny Liu");
		System.out.println(masked);
		System.out.println(unmaskAscii(masked));
	}

}
