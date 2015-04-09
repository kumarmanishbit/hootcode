package com.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.main.XMLFinder;

public class UrlValidation {

	@Test
	public void test() {
		
	
		XMLFinder xmlValidator=new XMLFinder();
		String content="<html></html>";
		
		    assertFalse(xmlValidator.chekcXml(content));
	}

}
