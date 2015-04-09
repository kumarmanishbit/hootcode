package com.main;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public class Emp {

	public static void main(String args[]) throws Exception {

		HashSet<String> urls = new HashSet<String>();

		StaticCrawle sw = new StaticCrawle();
		urls = sw.crawle(new URL("http://www.tutorialspoint.com/java/java_string_matches.htm"));

		for (Object item : new HashSet<Object>(urls)) {

		urls=sw.crawle(new URL((String) item));
		}
		
		Iterator<String> it=urls.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
			
		}

	}

}


