package com.asksunny.cli.utils;

import com.asksunny.cli.utils.annotation.CLIOptionBinding;

public class TestConfig {

	@CLIOptionBinding(shortOption='f', longOption="input")
	public String input;
	String output;
	boolean hasbool;
	
	
	
	public TestConfig() {		
	}




	public String getInput() {
		return input;
	}




	public void setInput(String input) {
		this.input = input;
	}




	public String getOutput() {
		return output;
	}



	@CLIOptionBinding(shortOption='o', longOption="output")
	public void setOutput(String output) {
		this.output = output;
	}




	public boolean isHasbool() {
		return hasbool;
	}



	@CLIOptionBinding(shortOption='b', longOption="bool")
	public void setHasbool(boolean hasbool) {
		this.hasbool = hasbool;
	}
	
	public static void main(String[] args)
	{
		String name = "ateat";		
		name = String.format("%C%s", name.charAt(0), name.length()>1?name.substring(1):"");
		System.out.println(name);
	}

}
