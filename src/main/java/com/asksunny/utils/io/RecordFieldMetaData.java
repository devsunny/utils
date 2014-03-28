package com.asksunny.utils.io;

public class RecordFieldMetaData {

	public static enum FIELD_TYPE {STRING, NUMBER, DATE, DATETIME, BLOB};
	
	FIELD_TYPE type = null;
	int maxLength;
	int minLength;
	boolean isdecimal;
	int maxDecimalLength;
	int maxIntLength = 0;
	boolean nullable;
	boolean negative;
	int dateConfidence = 0;  //10 date, 8, 9 very possible date; 6, 7 possible
	int timeCofidence = 0;
	
	
	
	public int getMaxIntLength() {
		return maxIntLength;
	}

	public void setMaxIntLength(int maxIntLength) {
		this.maxIntLength = maxIntLength;
	}

	public int getDateConfidence() {
		return dateConfidence;
	}

	public void setDateConfidence(int dateConfidence) {
		this.dateConfidence = dateConfidence;
	}

	public int getTimeCofidence() {
		return timeCofidence;
	}

	public void setTimeCofidence(int timeCofidence) {
		this.timeCofidence = timeCofidence;
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

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

	public RecordFieldMetaData() {		
	}

	@Override
	public String toString() {
		return "RecordFieldMetaData [type=" + type + ", maxLength=" + maxLength
				+ ", minLength=" + minLength + ", isdecimal=" + isdecimal
				+ ", maxDecimalLength=" + maxDecimalLength + ", maxIntLength="
				+ maxIntLength + ", nullable=" + nullable + ", negative="
				+ negative + ", dateConfidence=" + dateConfidence
				+ ", timeCofidence=" + timeCofidence + "]";
	}

	
	
}
