package com.asksunny.utils;


public class AlphaNumbericMaskUtil {
	
	public final static int[][] NUMERIC_MASKS = new int[][]{
		{0,5,7,9,4,1,6,3,8,2},
		{1,9,6,3,8,2,5,7,4,0},
		{4,2,1,0,6,7,5,3,9,8},
		{6,9,8,1,3,0,5,7,2,4},
		{5,6,2,8,4,9,7,0,3,1},
		{3,9,5,1,7,6,0,2,8,4},
		{2,6,4,9,8,1,0,7,5,3},
		{9,5,2,1,6,4,0,7,3,8},
		{5,7,1,0,9,6,8,4,2,3},
		{6,8,9,5,1,4,0,2,7,3},
		{5,8,3,4,1,0,7,6,9,2},
		{2,1,0,4,6,5,7,8,9,3},
		{5,2,4,8,9,6,0,3,1,7},
		{4,0,1,6,2,9,3,7,5,8},
		{2,9,8,5,0,1,3,6,4,7},
		{5,7,1,6,3,9,8,0,2,4},
		{3,7,6,5,8,4,2,9,0,1},
		{4,7,3,6,0,8,9,1,2,5},
		{0,4,2,9,8,1,6,5,7,3},
		{9,1,5,4,3,7,0,8,2,6},
		{0,2,9,3,8,5,4,6,7,1},
		{3,7,1,4,2,5,6,9,8,0},
		{4,5,9,2,1,6,0,3,7,8},
		{4,2,3,1,5,7,6,8,9,0},
		{0,2,5,8,3,1,4,9,7,6},
		{3,4,8,2,0,6,1,9,7,5},
		{1,6,0,4,5,7,9,3,8,2},
		{5,2,0,4,9,7,3,6,1,8},
		{6,0,3,8,1,5,4,9,7,2},
		{4,1,7,6,2,5,0,3,9,8},
		{8,3,5,7,0,9,4,1,2,6},
		{2,0,5,7,8,9,1,3,4,6},
		{6,4,8,7,3,9,0,2,1,5},
		{6,3,4,9,0,8,7,1,2,5},
		{1,0,9,3,7,5,8,4,2,6},
		{6,1,7,3,0,9,4,2,5,8},
		{3,8,5,9,7,0,4,2,1,6},
		{5,0,8,1,3,6,2,7,9,4},
		{5,0,9,6,4,7,1,8,3,2},
		{3,6,9,5,4,8,7,1,0,2},
		{1,0,6,9,3,8,4,2,5,7},
		{2,4,5,9,6,8,7,1,3,0},
		{2,6,7,1,5,4,0,8,3,9},
		{0,7,2,8,6,9,3,5,1,4},
		{3,2,8,4,6,7,1,5,0,9},
		{6,0,7,1,2,3,5,9,8,4},
		{3,6,8,4,5,0,1,2,9,7},
		{4,3,0,1,7,9,8,6,5,2},
		{8,7,4,0,1,5,3,9,2,6},
		{3,4,7,0,6,1,8,2,5,9},
		{1,3,4,5,6,7,8,9,0,2},
		{1,8,6,2,5,3,9,7,0,4},
		{6,2,9,4,7,8,0,3,1,5},
		{9,6,3,0,8,4,1,5,7,2},
		{4,6,3,7,5,9,1,8,2,0},
		{9,6,3,7,4,0,2,5,1,8},
		{2,1,0,5,4,9,8,3,6,7},
		{1,8,5,9,7,3,6,0,4,2},
		{9,2,3,7,1,0,4,5,6,8},
		{8,9,4,5,0,2,6,3,1,7},
		{8,1,6,5,9,7,3,0,4,2},
		{0,4,2,3,7,5,9,6,1,8},
		{1,3,7,9,2,6,0,4,8,5},
		{3,4,5,2,6,7,8,1,0,9},
		{8,4,6,9,3,5,1,2,7,0},
		{8,3,4,2,9,5,1,7,6,0},
		{6,2,4,7,3,8,0,9,1,5},
		{2,7,4,1,6,9,5,0,8,3},
		{4,8,7,5,0,2,1,6,3,9},
		{5,6,8,7,2,3,4,1,0,9},
		{2,4,9,5,0,1,7,8,3,6},
		{6,4,2,1,9,8,0,7,5,3},
		{6,4,1,7,9,5,3,8,0,2},
		{4,0,1,8,7,6,2,5,9,3},
		{8,7,1,5,3,2,6,4,9,0},
		{8,6,5,4,1,3,0,7,9,2},
		{1,3,8,0,2,5,6,9,4,7},
		{5,6,0,1,8,9,2,3,7,4},
		{3,8,9,7,2,1,4,6,5,0},
		{2,3,8,7,9,4,0,5,1,6},
		{9,1,8,2,7,5,4,6,3,0},
		{8,7,5,1,4,9,2,0,3,6},
		{3,7,4,0,2,5,8,6,9,1},
		{3,9,8,7,2,6,1,4,0,5},
		{3,8,2,0,6,7,5,4,1,9},
		{0,8,9,1,2,5,4,3,6,7},
		{0,1,9,2,8,6,3,7,5,4},
		{8,6,3,2,9,4,1,7,0,5},
		{3,1,0,7,8,9,4,5,2,6},
		{2,8,7,4,3,0,1,5,9,6},
		{3,2,0,8,1,4,9,7,6,5},
		{8,5,4,9,3,1,2,7,0,6},
		{0,9,2,1,6,3,4,8,7,5},
		{8,1,5,0,4,2,9,7,6,3},
		{4,8,5,2,0,6,3,9,7,1},
		{7,6,0,3,2,8,9,5,4,1},
		{7,4,5,8,0,9,2,1,3,6},
		{6,0,7,8,4,5,2,3,9,1},
		{8,1,9,6,5,4,3,2,7,0},
		{4,9,8,5,1,2,0,3,7,6},
		{5,2,8,0,4,9,6,7,1,3},
		{8,4,6,7,2,0,5,9,1,3},
		{0,1,4,6,3,8,7,9,5,2},
		{4,6,0,2,5,1,8,3,9,7},
		{9,3,7,2,1,5,8,6,4,0},
		{7,8,3,2,4,5,0,6,1,9},
		{7,6,2,9,4,1,8,3,0,5},
		{5,3,2,4,8,6,7,0,1,9},
		{1,3,2,8,9,5,4,0,6,7},
		{5,4,8,9,7,6,1,0,3,2},
		{8,9,2,1,3,7,6,4,0,5},
		{4,5,7,6,2,0,9,1,3,8},
		{0,4,1,7,2,6,3,9,8,5},
		{1,2,7,0,6,8,5,3,4,9},
		{0,1,2,5,6,3,4,9,7,8},
		{1,4,8,5,9,2,0,6,3,7},
		{6,2,5,1,8,7,3,4,0,9},
		{3,7,4,6,1,2,0,8,9,5},
		{6,4,0,2,3,7,9,8,1,5},
		{5,7,6,2,0,8,9,1,4,3},
		{9,1,2,8,3,0,5,6,7,4},
		{2,7,9,1,8,5,0,6,4,3},
		{1,4,8,0,7,6,9,5,3,2},
		{9,4,0,2,3,7,1,6,8,5},
		{7,3,0,2,9,6,5,4,8,1},
		{5,8,6,3,7,9,4,2,0,1},
		{1,7,5,4,3,6,8,9,0,2},
		{7,0,8,4,6,2,9,5,3,1},
		{6,8,4,1,5,9,0,7,2,3},
		{5,7,9,4,1,6,3,8,0,2}
	};
	
	
	
	
	public static int maskDigit(int number)
	{
		
		if(number > 999999999) throw new IllegalArgumentException("Exceed maximum allowed value exception, please use java.lang.long data type");
		StringBuffer numstr = new StringBuffer(Long.toString(number));		
		for(int i=0; i<numstr.length(); i++){
			char d = numstr.charAt(i);
			int x = d - 48;
			if(d<48 || d>57){
				;
			}else{
				int[] t = NUMERIC_MASKS[i];				
				numstr.setCharAt(i, (char)(t[x]+48));
			}
		}		
		return Integer.valueOf(numstr.toString()).intValue();
	}
	
	
	
	public static double maskDigit(double number)
	{
		
		StringBuffer numstr = new StringBuffer(Double.toString(number));		
		for(int i=0; i<numstr.length(); i++){
			char d = numstr.charAt(i);
			int x = d - 48;
			if(d<48 || d>57){
				;
			}else{
				int[] t = NUMERIC_MASKS[i];				
				numstr.setCharAt(i, (char)(t[x]+48));
			}
		}		
		return Double.valueOf(numstr.toString()).doubleValue();
	}
	
	
	public static long maskDigit(Long number)
	{
		
		StringBuffer numstr = new StringBuffer(Long.toString(number));		
		for(int i=0; i<numstr.length(); i++){
			char d = numstr.charAt(i);
			int x = d - 48;
			if(d<48 || d>57){
				;
			}else{
				int[] t = NUMERIC_MASKS[i];				
				numstr.setCharAt(i, (char)(t[x]+48));
			}
		}		
		return Long.valueOf(numstr.toString()).longValue();
	}
	
	public static String maskDigit(String numberstr)
	{
		
		StringBuffer numstr = new StringBuffer(numberstr);		
		for(int i=0; i<numstr.length(); i++){
			char d = numstr.charAt(i);
			int x = d - 48;
			if(d<48 || d>57){
				;
			}else{
				int[] t = NUMERIC_MASKS[i];				
				numstr.setCharAt(i, (char)(t[x]+48));
			}
		}		
		return numstr.toString();
	}
	
	
	public static int unmaskDigit(int masked)
	{
		
		String intStr = unmaskDigit(Integer.toString(masked));
		return Integer.valueOf(intStr);
	}
	
	public static double unmaskDigit(double masked)
	{		
		String intStr = unmaskDigit(Double.toString(masked));
		return Double.valueOf(intStr);
	}
	
	
	public static long unmaskDigit(long masked)
	{
		
		String intStr = unmaskDigit(Long.toString(masked));
		return Long.valueOf(intStr);
	}
	
	public static String unmaskDigit(String masked)
	{
		
		StringBuffer numstr = new StringBuffer(masked);		
		for(int i=0; i<numstr.length(); i++){
			char d = numstr.charAt(i);
			int x = d - 48;
			if(d<48 || d>57){
				;
			}else{
				int[] t = NUMERIC_MASKS[i];				
				int j=0;
				for(j=0; j<t.length; j++){
					if(t[j]==x) break;
				}
				numstr.setCharAt(i, (char)(j+48));
			}
		}
		return numstr.toString();
	}
	
	
	
	public AlphaNumbericMaskUtil() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		SecureRandom r = new SecureRandom(new Date().toString().getBytes());
//		for(int j=0; j<129; j++){
//			
//			int[] MASKS = new int[]{0,1,2,3,4,5,6,7,8,9};
//			ArrayList<Integer> MASKSV = new ArrayList<Integer>();
//			for(int i=0; i<10; i++){
//				MASKSV.add(MASKS[i]);
//			}			
//			System.out.print("{");
//			for(int i=0; i<10; i++){
//				int x = r.nextInt(MASKSV.size());
//				System.out.print(MASKSV.remove(x));
//				if(i<9) System.out.print(",");
//			}
//			System.out.print("},");
//		System.out.println("");
//		}
		int x = maskDigit(999999999);
		System.out.println(x);
		System.out.println(unmaskDigit(x));		
		String masked = maskDigit("123-789-4321");
		System.out.println(masked);
		System.out.println(unmaskDigit(masked));
		double d = maskDigit(1234.6789);
		System.out.println(d);
		System.out.println(unmaskDigit(d));

	}

}
