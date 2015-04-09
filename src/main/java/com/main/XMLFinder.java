package com.main;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This Class is responsible to check whether the content is xml or other content.
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */

public class XMLFinder {

	public  boolean chekcXml(String content) {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		String st = null;
		org.w3c.dom.Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(content));

			doc = db.parse(is);
			st = doc.getDocumentElement().getNodeName();
			System.out.println("Root element " + st);
		} catch (ParserConfigurationException e) {

			return false;
		} catch (SAXException e) {

			return false;
		} catch (IOException e) {

			return false;
		}

		return st == "mail";

	}
	
	
}
