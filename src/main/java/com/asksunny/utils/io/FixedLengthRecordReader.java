package com.asksunny.utils.io;

import java.io.IOException;
import java.io.Reader;

public class FixedLengthRecordReader  extends Reader {

	Reader reader = null;
	int[] fieldLengths = null;
	
	
	public FixedLengthRecordReader(Reader reader, int[] fieldLengths)
	{
		this.reader = reader;
		this.fieldLengths = fieldLengths;
	}
	
	
	
	
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {		
		return reader.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException {
		if(reader!=null) reader.close(); 
	}

}
