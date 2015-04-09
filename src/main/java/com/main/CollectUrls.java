package com.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.xml.sax.SAXException;

public class CollectUrls {

	private WebDriver driver;

	public CollectUrls() {
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}

	protected Set<String> getURl(String url) throws MalformedURLException {
	//	Queue<String> queue = new ConcurrentLinkedQueue<String>();

		if (!url.matches("(.*.)css"))
			this.driver.get(url);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		String content = this.driver.getPageSource();

		String val = url.replaceAll("\\D+", "");

		String fileMonth = val.isEmpty() ? val : val.substring(4, 6);

		// check content is final mail content or simply html data

		EmailContent emailContent = new EmailContent();
		XMLFinder xmlFinder = new XMLFinder();
		if (xmlFinder.chekcXml(content)) {
			try {

				emailContent.generateEmail(content, fileMonth);
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			return null;
		}

		// parsing urls in string

		URLFormatter format = new URLFormatter();
		return format.formatURL(content, url);

	}



}