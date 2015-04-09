package com.main;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
* The Crawler Entry point 
*
* @author  Manish Kumar
* @version 1.0
* @since   10-April-2015
*/
public class Crawler {
	

	// private static final Logger logger = Logger.getLogger(Crawler.class);

	public static void main(String[] args) throws FailingHttpStatusCodeException, IOException, InterruptedException {

		// Keeping all the sites to be visited in Hashset to avoid duplication 
		Set<String> urls = new LinkedHashSet<String>();


		CollectUrls grabUrl = new CollectUrls();
		// for static checking use this url
		//"http://www.tutorialspoint.com/java/java_string_matches.htm"
		
		String userURL = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		AJAXFinder checkPage = new AJAXFinder();

		if (!checkPage.checkAjax(new URL(userURL))) {

			HashSet<String> staticUrl = new HashSet<String>();

			StaticCrawle staticPages = new StaticCrawle();
			staticUrl = staticPages.crawle(new URL(userURL));

			for (Object item : new HashSet<Object>(urls)) {

				staticUrl = staticPages.crawle(new URL((String) item));
			}

			// staticUrlList
			Iterator<String> staticUrlList = staticUrl.iterator();

			while (staticUrlList.hasNext()) {
				System.out.println(staticUrlList.next());

			}

		} else {

			urls = grabUrl.getURl(userURL);

			System.out.println("Size of queue before processing is " + urls.size());
			HashMap<String, Set<String>> hm = new HashMap<String, Set<String>>();
			Set<String> linksToProcess = new LinkedHashSet<String>();
			Set<String> links = new LinkedHashSet<String>();
			URLFilter urlFilter = new URLFilter();
			linksToProcess = urlFilter.getMonth(urls);

			// linksToProcess.add("http://mail-archives.apache.org/mod_mbox/maven-users/201412.mbox/browser");
			// linksToProcess.add("http://mail-archives.apache.org/mod_mbox/maven-users/201411.mbox/browser");

			System.out.println("Size of queue is " + linksToProcess.size());

			links.addAll(linksToProcess);
			Iterator<String> startLink = linksToProcess.iterator();

			while (startLink.hasNext()) {
				String element = (String) startLink.next();
				System.out.println(element);

				links.addAll(grabUrl.getURl(element));
				// hm.put(element, cu.getURl(element));
			}

			System.out.println("Size of Map is " + hm.size());

			Iterator<String> linkToVisit = links.iterator();
			while (linkToVisit.hasNext()) {
				String element = (String) linkToVisit.next();
				System.out.println(element);
				grabUrl.getURl(element);
			}


		}

		System.exit(0);
	}

}
