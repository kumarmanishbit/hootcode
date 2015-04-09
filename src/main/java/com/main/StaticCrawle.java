package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class is responsible to grab the content which are not related to ajax or any javascript. 
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */


public class StaticCrawle {

	static HashSet<String> urls = new HashSet<String>();

	public HashSet<String> crawle(URL url) {

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));


			String inputLine;
			String content = "";
			// save to this filename

			String fileName = "static/" + url.getFile().replaceAll("/", "_") + ".txt";
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			// use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			while (((inputLine = br.readLine()) != null)) {

				bw.write(inputLine);
				content = content + inputLine;
			}

			bw.close();
			br.close();

			Pattern p = Pattern.compile("href=\"(.*?)\"");
			Matcher m = p.matcher(content);
			String tmp = null;

			String urlRegex = "\\b(https?|ftp|file|ldap)://" + "[-A-Za-z0-9+&@#/%?=~_|!:,.;]"
					+ "*[-A-Za-z0-9+&@#/%=~_|]";

			URL url1 = null;
			while (m.find()) {
				tmp = m.group(1);

				if (!tmp.matches(urlRegex)) {

					url1 = new URL(url, tmp);
					urls.add(url1.toString());
				} else {
					urls.add(tmp);
				}
			}
			// System.out.println("size of the url now is "+urls.size());
			return urls;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urls;

	}
}
