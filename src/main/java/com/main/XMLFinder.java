package com.main;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This Class is responsible to check whether the content is xml or other content.
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */

public class XMLFinder {

	private static final Logger logger = Logger.getLogger(XMLFinder.class);
	public  boolean chekcXml(String content) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		String root = null;
		org.w3c.dom.Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(content));

			doc = db.parse(is);
			root = doc.getDocumentElement().getNodeName();
			logger.info("Root Element is "+root);
			
		} catch (ParserConfigurationException e) {

			return false;
		} catch (SAXException e) {

			return false;
		} catch (IOException e) {

			return false;
		}

		return root == "mail";

	}
	
	
}
