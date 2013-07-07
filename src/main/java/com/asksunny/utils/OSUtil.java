package com.asksunny.utils;

public class OSUtil 
{
	public static boolean isUnixish()
	{
		return !System.getProperty("os.name").toLowerCase().contains("windows");
	}
	
	public static boolean isWindows()
	{
		return !System.getProperty("os.name").toLowerCase().contains("windows");
	}
	
	
	public static String OsLineSeparator()
	{
		return System.getProperty("line.separator");
	}
	
	public static String PathSeparator()
	{
		return System.getProperty("path.separator");
	}
	
	
	public static String FileSeparator()
	{
		return System.getProperty("file.separator");
	}
	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("line.separator"));
	}
}
