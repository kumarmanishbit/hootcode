package com.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * This Class is responsible to grab the data Input :- Pass the url of the
 * corresponding page
 * 
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */

public class CollectUrls {

	private static final Logger logger = Logger.getLogger(CollectUrls.class);

	public Set<String> getURl(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException,
			InterruptedException {

		@SuppressWarnings("deprecation")
		WebClient client = new WebClient(BrowserVersion.FIREFOX_17);
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setRedirectEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setUseInsecureSSL(true);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());

		Page page = client.getPage(url);

		logger.info("Processing : " + url);

		String content = ((SgmlPage) page).asXml();

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