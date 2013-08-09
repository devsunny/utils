package com.asksunny.tpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleTemplateEngine {

	
	String varCue;
	String varBegin;
	String varEnd;
	List<TmplEntry> compiledUnits = new ArrayList<SimpleTemplateEngine.TmplEntry>();
	
	
	
	public String render(Map<String, ? extends Object> parameters)
	{
		StringBuilder buf = new StringBuilder();
		for(TmplEntry entry: compiledUnits)
		{
			switch(entry.type)
			{
			case 1:
				Object obj =parameters==null?null:parameters.get(entry.text);
				if(obj!=null){
					buf.append(obj.toString());
				}else{
					if(varCue!=null) buf.append(varCue);
					if(varBegin!=null) buf.append(varBegin);
					 buf.append(entry.text);
					if(varEnd!=null) buf.append(varEnd);					
				}				
				break;
			default:
				buf.append(entry.text);
				break;
			}			
			
		}
		return buf.toString();
	}
	
	
	
	
	
	public void compile(String tmpl)
	{
		if(tmpl==null) throw new NullPointerException("Template could not be null");
		boolean hasCue = varCue!=null && varCue.length()>0;
		boolean hasSt = varBegin!=null && varBegin.length()>0;		
		int len = tmpl.length();		
		int cuelen = hasCue?varCue.length():0;
		int blen = hasSt?varBegin.length():0;
		
		
		StringBuilder buf = new StringBuilder();
		for(int index=0; index<len; index++)
		{
			char c = tmpl.charAt(index);
			if(hasCue && c==varCue.charAt(0)){
				int m =  tryMatch(tmpl, len, index, varCue, cuelen);
				if(m != -1){										
					if(hasSt && tmpl.charAt(m+1)==varBegin.charAt(0)){							
						int m3 = tryMatchVariable(tmpl, len, m, buf, blen);
						if(m3>-1){
							index = m3-1;
						}
					}else if((hasSt && tmpl.charAt(m+1)!=varBegin.charAt(0)) || !hasSt){										
						AddTextEntry(buf);	
						index = m;							
						for(index=m; index<len; index++){
							char c2 = tmpl.charAt(index);
							if(!Character.isJavaIdentifierPart(tmpl.charAt(index)) || index==len){
								break;
							}else{
								buf.append(c2);
							}
						}
						AddVarEntry(buf);
						index--;
											
					}
				}
			}else if (!hasCue && hasSt && c==varBegin.charAt(0)){
				int m3 = tryMatchVariable(tmpl, len, index, buf, blen);
				if(m3>-1){
					index = m3-1;
				}
			}else{
				buf.append(c);
			}
			
		}
		
		 AddTextEntry(buf);		
		
	}
	
	protected int tryMatchVariable(String src, int srcLength, int offset, StringBuilder buf, int tgtLength)
	{
		int m2 = tryMatch(src, srcLength, offset, varBegin, tgtLength);
		int m3 = -1;		
		int elen = varEnd.length();		
		if(m2>-1){	
			for(int k=m2; k<srcLength; k++){
				char c3 = src.charAt(k);									
				if(c3==varEnd.charAt(0)){									
					m3 = tryMatch(src, srcLength, k, varEnd, elen);
					if(m3>0){
						AddTextEntry(buf);	
						AddVarEntry(src.substring(m2, m3-elen));						
						break;
					}
				}								
			}							
		} //end of if (m2>-1)
		return m3;
	}
	
	protected void AddTextEntry(StringBuilder buf)
	{
		if(buf.length()==0) return;
		TmplEntry entry = new TmplEntry();
		entry.text = buf.toString();
		entry.type = 0;
		compiledUnits.add(entry);
		buf.setLength(0);
		
	}
	
	protected void AddVarEntry(StringBuilder buf)
	{
		TmplEntry entry = new TmplEntry();
		entry.text = buf.toString();
		entry.type = 1;
		compiledUnits.add(entry);
		buf.setLength(0);
			
	}
	
	protected void AddVarEntry(String buf)
	{
		TmplEntry entry = new TmplEntry();
		entry.text = buf.toString();
		entry.type = 1;
		compiledUnits.add(entry);
			
	}
	
	
	
	protected int tryMatch(String src, int srcLength, int offset, String tgt, int tgtLength)
	{
		
		if(tgtLength==1) return offset+1;
		
		int mh = offset + 1;
		int ci = 1;
		boolean match = true;
		for(; ci<tgtLength && mh<srcLength; ci++, mh++){						
			if( src.charAt(mh)!=tgt.charAt(ci)){
				match = false;							
				break;
			}						
		}// end of compare for loop;
		if(!match){
			mh = -1;
		}
		return mh;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("myvar2", "<Hello World>");
		System.out.println("---------------------Test case 1 --------------------------------");
		String test = "hello    test @@myvar test2";
		SimpleTemplateEngine ste = new SimpleTemplateEngine("@@", null, null);
		ste.compile(test);
		System.out.println(ste.render(param));		
		System.out.println("---------------------Test case 2 --------------------------------");
		test = "hello    test @@{{myvar}}test2  @@myvar2";
		ste = new SimpleTemplateEngine("@@", "{{", "}}");
		ste.compile(test);
		System.out.println(ste.render(param));
		System.out.println("---------------------Test case 3 --------------------------------");
		test = "hello    test {{myvar}}test2";
		ste = new SimpleTemplateEngine(null, "{{", "}}");
		ste.compile(test);
		param.put("myvar", "<dala...dala>");
		System.out.println(ste.render(param));
		
	}

	
	
	private static final class TmplEntry
	{
		public int type = 0; //0 - normal text, not translate
						 //1  - simple variable		
		public String text = null;
		
		
	}
	
	

	public SimpleTemplateEngine() {
		super();
		
	}

	

	public SimpleTemplateEngine(String varBegin, String varEnd) {
		super();
		this.varBegin = varBegin;
		this.varEnd = varEnd;
	}


	public SimpleTemplateEngine(String varCue) {
		super();
		this.varCue = varCue;
	}


	public SimpleTemplateEngine(String varCue, String varBegin, String varEnd) {
		super();
		this.varCue = varCue;
		this.varBegin = varBegin;
		this.varEnd = varEnd;
	}


	public String getVarCue() {
		return varCue;
	}




	public void setVarCue(String varCue) {
		this.varCue = varCue;
	}




	public String getVarBegin() {
		return varBegin;
	}




	public void setVarBegin(String varBegin) {
		this.varBegin = varBegin;
	}




	public String getVarEnd() {
		return varEnd;
	}




	public void setVarEnd(String varEnd) {
		this.varEnd = varEnd;
	}
	
	
	

}
