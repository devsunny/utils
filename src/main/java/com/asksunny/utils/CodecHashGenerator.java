package com.asksunny.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;

public class CodecHashGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SecureRandom r = new SecureRandom(new Date().toString().getBytes());
		StringBuilder buf1 = new StringBuilder();		
		StringBuilder buf3 = new StringBuilder();
		StringBuilder buf4 = new StringBuilder();
		
		buf1.append("public final static int[][] EXT_ASCII_TABLES = new int[][]{\n");
		for(int k=0; k<12; k++){
		
			int[] ascii = new int[256];	
			for (int i = 0; i < 32; i++) {			
				 ascii[i] = i;
			}
			ArrayList<Integer> MASKSV = new ArrayList<Integer>();
			for (int i = 32; i < 127; i++) {
				MASKSV.add(i);
			}			
			
			for (int i = 32; i < 127; i++) {
				 int x = r.nextInt(MASKSV.size());
				 int v = MASKSV.remove(x);
				 ascii[i] = v;
			}
			ascii[127] = 127;
			
			ArrayList<Integer> eascii = new ArrayList<Integer>();
			for (int i = 128; i < 255; i++) {
				eascii.add(i);
			}
			
			for (int i = 128; i < 255; i++) {
				int x = r.nextInt(eascii.size());
				int v = eascii.remove(x);
				 ascii[i] = v;
			}		
			 ascii[255] = 255;
			 
			buf1.append("{");
			for (int i = 0; i < 256; i++) {
				 if(i%10==0 && i>0) buf1.append("\n");
				 buf1.append(ascii[i]);
				 if(i<255) buf1.append(", ");
			}
			buf1.append("}");
			if(k<11) buf1.append(",");
			
			buf3.append(String.format("private final static ConcurrentHashMap<Integer, Integer> ASCII_LOOKUP_HASH_%d = new ConcurrentHashMap<Integer, Integer>();\n", k));
			
			for (int i = 0; i < 256; i++) {			
				buf4.append(String.format("ASCII_LOOKUP_HASH_%d.put(%d, %d);\n", k, ascii[i], i));			
			}
			
		}
		buf1.append("};\n");
		
		
		
		StringBuilder bufx = new StringBuilder();
		bufx.append(buf1);
		bufx.append(buf3);
		bufx.append("public final static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>> ASCII_LOOKUP_HASHS \n= new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Integer>>();\n");
		bufx.append("static {\n");
		bufx.append(buf4);
		for(int k=0; k<12; k++){
			bufx.append(String.format("ASCII_LOOKUP_HASHS.put(%d, ASCII_LOOKUP_HASH_%d);\n", k, k));
		}
		bufx.append("}\n");
		
		try {
			PrintWriter out = new PrintWriter("target/out.txt");
			out.println(bufx);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
