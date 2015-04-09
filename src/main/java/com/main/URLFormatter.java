package com.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLFormatter {

	public Set<String> formatURL(String content, String url) throws MalformedURLException {

		Set<String> queue = new LinkedHashSet<String>();
		//Queue<String> queue = new ConcurrentLinkedQueue<String>();
		// pattern
		Pattern p = Pattern.compile("href=\"(.*?)\"");
		// matcher
		Matcher m = p.matcher(content);
		
		// string extractUrl
		String urls = null;

		// updatedUrl
		URL url1 = null;

		String urlRegex = "\\b(https?|ftp|file|ldap)://" + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]" + "*[-A-Za-z0-9+&@#/%=~_|]";

		while (m.find()) {
			urls = m.group(1);

			if (!urls.matches(urlRegex)) {

				url1 = new URL(new URL(url), urls);

				if (url1.toString().matches(".*?2014.*?"))
					queue.add(url1.toString());
			} else {
				if (urls.matches(".*?2014.*?"))
					queue.add(urls);
			}
		}

		return queue;

	}

}
