package com.asksunny.utils.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordMetaProfiler 
{
	RecordFieldMetaData[] fieldMetaDatas = null;
	public final static String NUMBER_PATTERN_REGEX = "^(-)?(\\d+)?((\\.\\d+)((E|e)(\\d+))?)?$";
	public final static Pattern  NUMBER_PATTERN = Pattern.compile(NUMBER_PATTERN_REGEX);
	public final static Pattern  DATE_PATTERN =  Pattern.compile("^(((1|0|2|3)\\d)([/\\-_])((1|0|2|3)\\d)([/\\-_])\\d{4})|(\\d{4}([/\\-_])((1|0|2|3)\\d)([/\\-_])((1|0|2|3)\\d))$");
	public final static Pattern  TIME_PATTERN	
	=  Pattern.compile("^((((1|0|2|3)\\d)([/\\-_])((1|0|2|3)\\d)([/\\-_])\\d{4})|(\\d{4}([/\\-_])((1|0|2|3)\\d)([/\\-_])((1|0|2|3)\\d)))([\\-_ ])[0-2]\\d(([:\\-_])[0-5]\\d(([:\\-_])[0-5]\\d)?)?$");
	
	
	protected void updateMetaData(String value, RecordFieldMetaData md) 
	{
		
		if(value==null || value.trim().length()==0) {
			md.setNullable(true);
			return;
		}
		String str = value.trim();		
		if(md.getTimeCofidence()!=-1 &&  md.getType()!=RecordFieldMetaData.FIELD_TYPE.STRING &&  TIME_PATTERN.matcher(str).find()){
			md.setType(RecordFieldMetaData.FIELD_TYPE.DATETIME);
			md.setTimeCofidence(10);
			md.setDateConfidence(-1);
		}else if(md.getDateConfidence()!=-1 && md.getType()!=RecordFieldMetaData.FIELD_TYPE.STRING && DATE_PATTERN.matcher(str).find()){
			md.setTimeCofidence(-1);
			md.setDateConfidence(10);	
			md.setType(RecordFieldMetaData.FIELD_TYPE.DATE);
		}else{		
			Matcher matcher =  NUMBER_PATTERN.matcher(str);
			if(matcher.find()){			
				md.setType(RecordFieldMetaData.FIELD_TYPE.NUMBER);
				if(matcher.group(4)!=null){				
					md.setIsdecimal(true);
					if(matcher.group(5)==null && matcher.group(4).length()-1>md.getMaxDecimalLength()){
						md.setMaxDecimalLength(matcher.group(4).length()-1);
					}
					md.setDateConfidence(-1);
				}
				
				String g2 = matcher.group(2);					
				if(g2!=null && g2.length()>md.getMaxIntLength()){
					md.setMaxIntLength(g2.length());
				}
				
				if(matcher.group(1)!=null && matcher.group(1).equals("-")){
					md.setNegative(true);
				}
				if(md.getMinLength()==0 || md.getMinLength()>str.length()){
					md.setMinLength(str.length());
				}
				
				if(md.getMaxLength()==0 || md.getMaxLength()<str.length()){
					md.setMaxLength(str.length());
				}
				
				
				if(md.getDateConfidence()>=0 && md.getMaxLength()==md.getMinLength() && 
						(md.getMaxLength()==6 || md.getMaxLength()==8 || md.getMaxLength()==10 || md.getMaxLength()==12 || md.getMaxLength()==14) ){
					if(md.getMaxLength()>8 ){
						if(str.startsWith("0") && md.getDateConfidence()<10){
							md.setDateConfidence(8);
							md.setTimeCofidence(10);
							md.setType(RecordFieldMetaData.FIELD_TYPE.DATETIME);
						}else{
							md.setDateConfidence(8);
							md.setTimeCofidence(8);
							md.setType(RecordFieldMetaData.FIELD_TYPE.DATE);
						}
					}else{
						md.setType(RecordFieldMetaData.FIELD_TYPE.DATE);
						if(str.startsWith("0") && md.getDateConfidence()<10){
							md.setDateConfidence(10);
							md.setTimeCofidence(-1);
						}else{
							md.setDateConfidence(8);
							md.setTimeCofidence(-1);
						}
					}
				}			
				for(int i=1; i<=matcher.groupCount(); i++){
					System.out.println(i + ":" + matcher.group(i));
				}
			}else{				
				md.setType(RecordFieldMetaData.FIELD_TYPE.STRING);
				if(md.getMinLength()==0 || md.getMinLength()>str.length()){
					md.setMinLength(str.length());
				}
				if(md.getMaxLength()==0 || md.getMaxLength()<str.length()){
					md.setMaxLength(str.length());
				}				
			}		
		}
	}
	
	public static void main(String[] args) {
		RecordMetaProfiler profiler = new RecordMetaProfiler();
		RecordFieldMetaData md = new RecordFieldMetaData();
		//profiler.updateMetaData("123", md);
		profiler.updateMetaData("2014/01/02 23-21-35", md);	
		System.out.println(md);
		md = new RecordFieldMetaData();
		profiler.updateMetaData("2014/01/02", md);
		System.out.println(md);
		md = new RecordFieldMetaData();
		profiler.updateMetaData("2014-01-02", md);	
		System.out.println(md);
		md = new RecordFieldMetaData();
		profiler.updateMetaData("01-01-2014", md);
		System.out.println(md);
		md = new RecordFieldMetaData();
		profiler.updateMetaData("This is a date 12/31/1900", md);
		profiler.updateMetaData("This is a date 12/31/1900 addition data", md);
		profiler.updateMetaData("This is a date 12/31/1900 even longer text here", md);
		profiler.updateMetaData("01-01-2014", md);
		System.out.println(md);
		
		md = new RecordFieldMetaData();
		profiler.updateMetaData("1123", md);
		System.out.println(md);
		
		md = new RecordFieldMetaData();
		profiler.updateMetaData("24.123456", md);		
		System.out.println(md);
	}
}
