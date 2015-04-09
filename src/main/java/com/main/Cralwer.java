package com.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

public class Cralwer {

	// private static final Logger logger = Logger.getLogger(Crawler.class);

	public static void main(String[] args) throws MalformedURLException {

		// Queue<String> urls = new ConcurrentLinkedQueue<String>();
		Set<String> urls = new LinkedHashSet<String>();

		CollectUrls cu = new CollectUrls();
		String userURL = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		AJAXFinder checkPage = new AJAXFinder();

		
		if (!checkPage.checkAjax(new URL(userURL))) {

			HashSet<String> urls1 = new HashSet<String>();

			StaticCrawle sw = new StaticCrawle();
			urls1 = sw.crawle(new URL(userURL));

			for (Object item : new HashSet<Object>(urls)) {

				urls1 = sw.crawle(new URL((String) item));
			}

			Iterator<String> it = urls.iterator();

			while (it.hasNext()) {
				System.out.println(it.next());

			}

		} else {

			urls = cu.getURl(userURL);

			System.out.println("Size of queue before processing is " + urls.size());
			HashMap<String, Set<String>> hm = new HashMap<String, Set<String>>();
			Set<String> linksToProcess = new LinkedHashSet<String>();
			// Queue<String> linksToProcess = new
			// ConcurrentLinkedQueue<String>();
			URLFilter urlFilter = new URLFilter();
			linksToProcess = urlFilter.getMonth(urls);

			// linksToProcess.add("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/browser");
			// linksToProcess.add("http://mail-archives.apache.org/mod_mbox/maven-users/201411.mbox/browser");

			System.out.println("Size of queue is " + linksToProcess.size());

			Iterator<String> it1 = linksToProcess.iterator();

			while (it1.hasNext()) {
				String element = (String) it1.next();
				System.out.println(element);

				hm.put(element, cu.getURl(element));
			}

			System.out.println("Size of Map is " + hm.size());

			for (Entry<String, Set<String>> entry : hm.entrySet()) {
				// String key = entry.getKey();
				Iterator<String> value = entry.getValue().iterator();

				String currentUrl;
				while (value.hasNext()) {
					currentUrl = value.next();

					cu.getURl(currentUrl);

				}

			}

		}

		System.exit(0);
	}

}
