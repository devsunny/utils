package com.asksunny.utils.io;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DelimitedTextReader extends Reader {

	
	Reader reader = null;
	LookAheadReader preader = null;
	String rowDelimiter;
	String colDelimiter;
	StringBuilder buf = new StringBuilder();
	boolean includeTrailEmptyField = true;
	char[] discardBuf = null;
	
	int rdLen = 1;
	int cdLen = 1;
	
	public DelimitedTextReader(Reader reader) 
	{
		this(reader, "\n", ",", true);
	}

	public DelimitedTextReader(Reader reader, String rowDelimiter,
			String colDelimiter)
	{
		this(reader, rowDelimiter, colDelimiter, true);
	}
	public DelimitedTextReader(Reader reader, String rowDelimiter,
			String colDelimiter, boolean includeTrailEmptyField) 
	{
		super();
		this.reader = reader;
		this.rowDelimiter = rowDelimiter;
		this.colDelimiter = colDelimiter;
		this.rdLen = this.rowDelimiter.length();
		this.cdLen = this.colDelimiter.length();
		int size = (this.rdLen > this.cdLen)?this.rdLen:this.cdLen;
		this.preader = new LookAheadReader(reader, size);
		this.discardBuf = new char[size];
		this.includeTrailEmptyField = includeTrailEmptyField;
	}

	
	public List<String> readRecord() throws IOException
	{
		List<String> record = new ArrayList<String>();
		int i = readRecord(record);
		if(i==-1){
			return null;
		}else{
			return record;
		}
	}
	
	public int readRecord(List<String> recordCollector) throws IOException
	{
		int c = -1;
		boolean recordEnd = false;
		while(!recordEnd)
		{
			c = this.preader.read();
			if(c==-1){
				recordEnd = true;
				return -1;
			}else{
				if(c==rowDelimiter.charAt(0)){
					if(rdLen == 1){
						if( buf.length()>0 || (includeTrailEmptyField && buf.length()==0 ) ){
							recordCollector.add(buf.toString());
						}
						buf.setLength(0);	
						recordEnd = true;
						break;
					}else{
						String peek = this.preader.peek();
						int m = tryMatch(peek, 0, rowDelimiter, 1);						
						if(m==-1){
							buf.append(c);
						}else{
							if( buf.length()>0 || (includeTrailEmptyField && buf.length()==0 ) ){
								recordCollector.add(buf.toString());
							}
							buf.setLength(0);
							this.preader.read(this.discardBuf, 0, m);
							break;
						}						
					}
				}else if (c==colDelimiter.charAt(0)){					
					if(cdLen == 1){
						recordCollector.add(buf.toString());
						buf.setLength(0);	
					}else{
						String peek = this.preader.peek();
						int m = tryMatch(peek, 0, colDelimiter, 1);	
						if(m==-1){
							buf.append(c);
						}else{							
							recordCollector.add(buf.toString());							
							buf.setLength(0);
							this.preader.read(this.discardBuf, 0, m);							
						}		
					}			
				}else{
					buf.append((char)c);
				}
			}
		}	
		return 1;
	}
	
	
	protected int tryMatch(String src, int soff, String tgt, int toff)
	{
		
		int len = tgt.length();
		if(len==toff) return 0;
		int ret = len - toff;
		for(int i=toff; i<len; i++)
		{
			if(tgt.charAt(i)!=src.charAt(soff++)){
				ret = -1;
				break;
			}
		}
		return ret;		
	}
	
	

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {		
		return preader.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException {
		if(reader!=null) reader.close(); 
	}

}
