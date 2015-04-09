package com.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class is responsible to format the URL . We will have to format the relative url to absoulute URL.
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */


public class URLFormatter {

	public Set<String> formatURL(String content, String url) throws MalformedURLException {

		Set<String> queue = new LinkedHashSet<String>();
		
		Pattern pattern = Pattern.compile("href=\"(.*?)\"");
		
		Matcher matcher = pattern.matcher(content);
		
		
		String extractUrl = null;

		
		URL updatedUrl = null;

		String urlRegex = "\\b(https?|ftp|file|ldap)://" + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]" + "*[-A-Za-z0-9+&@#/%=~_|]";

		while (matcher.find()) {
			extractUrl = matcher.group(1);

			if (!extractUrl.matches(urlRegex)) {

				updatedUrl = new URL(new URL(url), extractUrl);

				if (updatedUrl.toString().matches(".*?2014.*?"))
					queue.add(updatedUrl.toString());
			} else {
				if (extractUrl.matches(".*?2014.*?"))
					queue.add(extractUrl);
			}
		}

		return queue;

	}

}
