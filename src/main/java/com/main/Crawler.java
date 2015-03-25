package com.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Crawler implements Runnable {

	private String uri;

	public Crawler(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void run() {

		try {
			System.out.println(this.uri);
			sendGet(this.uri);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static synchronized String callURL(String myURL) {
		
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}

		return sb.toString();
	}

	private static synchronized void sendGet(String uri) throws Exception {

		String url = uri + ".mbox/ajax/thread?0";

		// http://mail-archives.apache.org/mod_mbox/maven-users/201408

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();


		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(response.toString()));

		org.w3c.dom.Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("message");

		
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			System.out.println("Id: " + element.getAttribute("id"));
			// System.out.println("calling ........");
			getFullMessage(uri,element.getAttribute("id"));

		}

	}

	private static synchronized void getFullMessage(String uri,String id) throws Exception {

		String url = uri+".mbox/ajax/" + id;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(response.toString()));

		org.w3c.dom.Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("mail");

		File file = new File("mail"+".txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);

		// iterate the mail
		System.out.println("length of mail is " + nodes.getLength());

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			System.out.println("Id: " + element.getAttribute("id"));

			NodeList name = element.getElementsByTagName("from");
			Element line = (Element) name.item(0);
			// System.out.println("Name: " + line.getTextContent());
			bw.write("Name  :- " + line.getTextContent() + "\n");

			NodeList subject = element.getElementsByTagName("subject");

			line = (Element) subject.item(0);

			// System.out.println("Subject: " + line.getTextContent());

			bw.write("Subject  :-" + line.getTextContent() + "\n");

			NodeList content = element.getElementsByTagName("contents");

			line = (Element) content.item(0);

			// System.out.println("contents:- " + line.getTextContent());

			bw.write("contents:- " + line.getTextContent() + "\n\n");
			bw.write("================\n\n");

			// System.out.println("====================\n\n");

		}
		bw.close();
	}

}
