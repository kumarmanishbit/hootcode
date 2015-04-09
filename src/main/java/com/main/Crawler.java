package com.main;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

/**
 * The Crawler Entry point
 *
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */
public class Crawler {

	private static final Logger logger = Logger.getLogger(Crawler.class);

	public static void main(String[] args) throws FailingHttpStatusCodeException, IOException, InterruptedException {

		// Keeping all the sites to be visited in Hashset to avoid duplication
		Set<String> urls = new LinkedHashSet<String>();

		logger.info("Starting Crawling...");
		CollectUrls grabUrl = new CollectUrls();
		// for static checking use this url
		// "http://www.tutorialspoint.com/java/java_string_matches.htm"

		String userURL = "http://mail-archives.apache.org/mod_mbox/maven-users/";
		// Finding page source information
		AJAXFinder checkPage = new AJAXFinder();

		if (!checkPage.checkAjax(new URL(userURL))) {

			HashSet<String> staticUrl = new HashSet<String>();

			StaticCrawle staticPages = new StaticCrawle();
			staticUrl = staticPages.crawle(new URL(userURL));

			for (Object item : new HashSet<Object>(urls)) {

				staticUrl = staticPages.crawle(new URL((String) item));
			}

			// iterating over list of url present in the set.
			Iterator<String> staticUrlList = staticUrl.iterator();

			// This module is not our concern as of now
			while (staticUrlList.hasNext()) {
				System.out.println(staticUrlList.next());

			}

		} else {

			logger.info("Grabing the url " + userURL);
			urls = grabUrl.getURl(userURL);

			logger.info("Size of queue before processing is " + urls.size());
			Set<String> linksToProcess = new LinkedHashSet<String>();
			Set<String> links = new LinkedHashSet<String>();
			URLFilter urlFilter = new URLFilter();
			linksToProcess = urlFilter.getMonth(urls);

			links.addAll(linksToProcess);
			Iterator<String> startLink = linksToProcess.iterator();

			while (startLink.hasNext()) {
				String element = (String) startLink.next();
				links.addAll(grabUrl.getURl(element));

			}

			Iterator<String> linkToVisit = links.iterator();
			while (linkToVisit.hasNext()) {
				String element = (String) linkToVisit.next();

				grabUrl.getURl(element);
			}

		}

		System.exit(0);
	}

}
