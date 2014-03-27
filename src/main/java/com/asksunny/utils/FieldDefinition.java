package com.asksunny.utils;

public class FieldDefinition {

	public static enum FIELD_TYPE {STRING, NUMBER, DATE, DATETIME, BLOB};
	
	FIELD_TYPE type;
	int maxLength;
	int minLength;
	boolean isdecimal;
	int maxDecimalLength;
	
	
	
	public FIELD_TYPE getType() {
		return type;
	}

	public void setType(FIELD_TYPE type) {
		this.type = type;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public boolean isIsdecimal() {
		return isdecimal;
	}

	public void setIsdecimal(boolean isdecimal) {
		this.isdecimal = isdecimal;
	}

	public int getMaxDecimalLength() {
		return maxDecimalLength;
	}

	public void setMaxDecimalLength(int maxDecimalLength) {
		this.maxDecimalLength = maxDecimalLength;
	}

	public FieldDefinition() {		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
