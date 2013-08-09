package com.asksunny.utils.io;

import java.io.IOException;
import java.io.InputStream;

public class LookAheadInputStream extends InputStream {

	InputStream original;
	byte[] peekBuffer = null;
	int peekBufferSize = 0;
	
	
	public LookAheadInputStream(InputStream in, int peekBufferSize) {
		super();
		this.original = in;
		this.peekBufferSize = peekBufferSize;
		peekBuffer = new byte[this.peekBufferSize];
	}
	
	
	
	public byte[] peek()
	{
		return null;
	}
	
	public LookAheadInputStream(InputStream in) {	
		this(in , 1);
	}

	@Override
	public int read() throws IOException {
		
		return 0;
	}

	
	
	@Override
	public void close() throws IOException {
		this.original.close();
	}
	

}
