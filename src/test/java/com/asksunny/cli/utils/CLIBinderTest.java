package com.asksunny.cli.utils;

import static org.junit.Assert.*;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Test;

public class CLIBinderTest {

	@Test
	public void testBindPosix() {
		String[] args = new String[]{"-f", "path_to_file", "-b", "-o", "path_to_out", "arg1", "arg2", "arg3"};
		Options options = new Options();		
		options.addOption(new Option("f", "input", true, "path_to_file"));
		options.addOption(new Option("o", "output", true, "path_to_file"));
		options.addOption(new Option("b", "bool", false, "boolean value"));
		
		TestConfig cfg = new TestConfig();		
		CLIOptionAnnotationBasedBinder.bindPosix(options, args, cfg);
		
		assertEquals("path_to_file", cfg.getInput());
		assertEquals(true, cfg.isHasbool());
	}

}
