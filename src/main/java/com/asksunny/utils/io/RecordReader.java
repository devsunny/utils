package com.asksunny.utils.io;

import java.io.IOException;
import java.io.Reader;

public abstract class RecordReader extends Reader 
{
	public abstract Record nextRecord() throws IOException;
}
