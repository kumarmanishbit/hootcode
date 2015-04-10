package com.junit;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.main.URLFilter;

public class URLFilterTest {

	@Test
	public void test() {
		Set<String> urls = new LinkedHashSet<String>();
		urls.add("http://mail-archives.apache.org/mod_mbox/maven-users");
		URLFilter filter = new URLFilter();

		Iterator<String> urlsIter = filter.getMonth(urls).iterator();
		String filterUrl;

		while (urlsIter.hasNext()) {
			filterUrl = urlsIter.next();

			// System.out.println(st+" URL  "+st.matches(".*?2014.*?"));

			assertTrue(filterUrl.matches(".*?2014.*?"));

		}

	}
}
