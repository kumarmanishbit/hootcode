package com.main;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class URLFilter {

	public Set<String> getMonth(Set<String> urls) {

		// Iterator urlsIter
		Iterator<String> it1 = urls.iterator();
		String st;
	//	Queue<String> links = new ConcurrentLinkedQueue<String>();
		Set<String> links = new LinkedHashSet<String>();
		while (it1.hasNext()) {
			st = it1.next();

			// System.out.println(st+" URL  "+st.matches(".*?2014.*?"));
			if (st.matches(".*?2014.*?"))
				links.add(st);
		}
		System.out.println("Size of the queue is " + links.size());
		return links;
	}

}
