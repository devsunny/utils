package com.asksunny.utils.io;

import java.io.IOException;
import java.io.Reader;

public class LookAheadReader extends Reader 
{

	Reader reader = null;
	char[] lookAheadBuf = null;
	int bufferLen = 0;
	int bufferReadPosition = -1;
	int maxLookAheadSize = 0;

	public LookAheadReader(int maxLookAheadSize, Reader reader) {
		super();	
		this.reader = reader;
		this.maxLookAheadSize = maxLookAheadSize<1?1:maxLookAheadSize;
		lookAheadBuf = new char[this.maxLookAheadSize];
		
	}
		

	@Override
	public boolean markSupported() {		
		return false;
	}
	
	public boolean matchLookAhead(CharSequence cmpTgt, int offset, int len) throws IOException
	{
		if(len==0) return true;
		boolean ret = false;
		if(bufferLen==0 || bufferReadPosition>=bufferLen-1){
			bufferLen = reader.read(lookAheadBuf, 0, this.maxLookAheadSize);
			bufferReadPosition = -1;
		}
		
		
		
		return ret;
	}


	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		int left =(bufferReadPosition==-1)? bufferLen:(bufferLen - bufferReadPosition+1);
		if(left>=len){
			System.arraycopy(lookAheadBuf, ++bufferReadPosition, cbuf, off, len);
			
		}
		
		
		
		return 0;
	}

	
	
	@Override
	public void close() throws IOException {		
		if(reader!=null) reader.close();
	}

}
