package com.asksunny.utils.io;

import java.io.IOException;
import java.io.Reader;

public class LookAheadReader extends Reader {

	int peekSize = 0;
	Reader original = null;
	char[] peekBuffer = null;
	int consumed = 0;
	int bufferLength = 0;
	boolean eod = false;

	public LookAheadReader(Reader reader) {
		this(reader, 256);
	}

	public LookAheadReader(Reader reader, int peekSize) {
		if (peekSize < 0)
			throw new IllegalArgumentException(
					"peekable buffer size can not be negative number.");
		peekBuffer = new char[peekSize];
		this.peekSize = peekSize;
		this.original = reader;

	}

	public String peek() throws IOException {
		if (peekSize == 0)
			return "";
		if (bufferLength == 0 && !eod) {
			bufferLength = this.original.read(peekBuffer, 0, this.peekSize);
			if (bufferLength < this.peekSize) {
				eod = true;
			}
		} else if (bufferLength == 0 && eod) {
			return null;
		} else if (bufferLength < this.peekSize) {
			int addread = this.original.read(peekBuffer, bufferLength,
					this.peekSize - bufferLength);
			if (addread < this.peekSize - bufferLength) {
				eod = true;
			}
			if (addread != -1) {
				bufferLength = bufferLength + addread;
			}
		}
		return new String(peekBuffer, 0, bufferLength);
	}

	@Override
	public void close() throws IOException {
		if (original != null)
			original.close();
	}

	@Override
	public int read(char[] buf, int offset, int length) throws IOException {
		int ret = 0;
		if (bufferLength > 0) {
			if (length < bufferLength) {
				System.arraycopy(peekBuffer, 0, buf, offset, length);
				ret = length;
				char[] tmp = new char[this.peekSize];
				System.arraycopy(peekBuffer, length, tmp, 0, bufferLength
						- length);
				bufferLength = bufferLength - length;
				this.peekBuffer = tmp;
			} else {
				System.arraycopy(peekBuffer, 0, buf, offset, bufferLength);
				int rlen = length - bufferLength;
				int roffset = offset + bufferLength;
				ret = this.original.read(buf, roffset, rlen);
				if (ret < rlen)
					eod = true;
				if (ret != -1) {
					ret += bufferLength;
				} else {
					ret = bufferLength;
				}
				bufferLength = 0;
			}
		} else if (bufferLength == 0 && !eod) {
			ret = this.original.read(buf, offset, length);
		} else if (bufferLength == 0 && eod) {
			ret = -1;
		}
		return ret;
	}

}
