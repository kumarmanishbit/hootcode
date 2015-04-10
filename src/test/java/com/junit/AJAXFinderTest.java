package com.junit;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.main.AJAXFinder;

public class AJAXFinderTest {

	@Test
	public void test() {
		AJAXFinder ajaxFinder=new AJAXFinder();
		URL url=null;
		try {
			url = new URL("http://mail-archives.apache.org/mod_mbox/maven-users/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(ajaxFinder.checkAjax(url));
	}

}
