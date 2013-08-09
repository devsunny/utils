package com.asksunny.tmpl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import com.asksunny.tpl.SimpleTemplateEngine;

public class SimpleTemplateEngineTest {

	@Test
	public void testRender1() {
		String test = "hello    test @@myvar test2";
		SimpleTemplateEngine ste = new SimpleTemplateEngine("@@", null, null);
		ste.compile(test);
		assertEquals("hello    test @@myvar test2", ste.render(null));	
	}
	
	@Test
	public void testRender2() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("myvar2", "<Hello World>");
		param.put("myvar", "tada... tada... ");
		
		String test = "hello    test @@{{myvar}}test2  @@myvar2 b";
		SimpleTemplateEngine ste = new SimpleTemplateEngine("@@", "{{", "}}");
		ste.compile(test);
		assertEquals("hello    test tada... tada... test2  <Hello World> b", ste.render(param));
	}
	
	
	@Test
	public void testRender3() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("myvar2", "<Hello World>");
		String test = "hello    test @@{{myvar}}test2  @@myvar2";
		SimpleTemplateEngine ste = new SimpleTemplateEngine("@@", "{{", "}}");
		ste.compile(test);
		assertEquals("hello    test @@{{myvar}}test2  <Hello World>", ste.render(param));	
	}
	
	@Test
	public void testRender4() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		String test = "hello    test {{myvar}}test2";
		SimpleTemplateEngine ste = new SimpleTemplateEngine(null, "{{", "}}");
		ste.compile(test);
		param.put("myvar", "<dala...dala>");		
		assertEquals("hello    test <dala...dala>test2", ste.render(param));	
	}
	
	
}
