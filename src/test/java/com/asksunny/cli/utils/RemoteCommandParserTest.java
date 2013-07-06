package com.asksunny.cli.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.asksunny.cli.utils.RemoteCommand;
import com.asksunny.cli.utils.RemoteCommandParser;

public class RemoteCommandParserTest {

	@Test
	public void testParseCommand() {
		String test = "cmd.exe /c 'dir src \"another directory\"';exit";
		 RemoteCommand[]  cmds = RemoteCommandParser.parseCommand(test);
		 assertEquals(2, cmds.length);
		 assertEquals("dir src \"another directory\"", cmds[0].getCmdArray()[2]);
		 assertEquals("exit", cmds[1].getCmdArray()[0]);		 
	}

}
