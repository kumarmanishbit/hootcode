package com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class is responsible to check whether the page is Ajax enalbe or not
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */


public class AJAXFinder {

	public boolean checkAjax(URL url) {

		try {
			// get URL content

			URLConnection conn = url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine = "";
			String content = "";

			while ((inputLine = br.readLine()) != null) {

				content = content + inputLine;

			}

			br.close();

			if (content.matches("(.*)new XMLHttpRequest()(.*)")) {
				return true;
			} else if (content.matches("(.*).js(.*)")) {

				Pattern p = Pattern.compile("src=\"(.*?)\"");
				Matcher m = p.matcher(content);

				URL url1 = null;
				while (m.find()) {

					url1 = new URL(url, m.group(1));
				}

				content = "";

				conn = url1.openConnection();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				while ((inputLine = br.readLine()) != null) {

					content = content + inputLine;

				}
				if (content.matches("(.*)new XMLHttpRequest()(.*)"))
					return true;

			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
}
