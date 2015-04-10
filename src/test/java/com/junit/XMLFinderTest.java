package com.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.main.XMLFinder;

public class XMLFinderTest {

	@Test
	public void test() {
		XMLFinder xmlTest=new XMLFinder();
		String xmlContent="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<mail id=\"%3c547C1A5F.7070709@uni-jena.de%3e\"></mail>";
		assertTrue(xmlTest.chekcXml(xmlContent));
	}

}
