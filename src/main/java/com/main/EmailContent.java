package com.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This Class is responsible to grab the email and save them to corresponding file (month wise).
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */

public class EmailContent {

	public void generateEmail(String content, String fileMonth) throws IOException, ParserConfigurationException,
			SAXException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(content));

		org.w3c.dom.Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("mail");

		Map<String, String> month = new HashMap<String, String>();
		month.put("01", "Jan");
		month.put("02", "Feb");
		month.put("03", "March");
		month.put("04", "April");
		month.put("05", "May");
		month.put("06", "June");
		month.put("07", "July");
		month.put("08", "Aug");
		month.put("09", "Sep");
		month.put("10", "Oct");
		month.put("11", "Nov");
		month.put("12", "Dec");

		File file = new File("ajax/mail" + month.get(fileMonth) + ".txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);

		// iterate the mail

		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);

			// System.out.println("Id: " + element.getAttribute("id"));

			NodeList name = element.getElementsByTagName("from");
			Element line = (Element) name.item(0);

			bw.write("Name  :- " + line.getTextContent() + "\n");

			NodeList subject = element.getElementsByTagName("subject");

			line = (Element) subject.item(0);

			bw.write("Subject  :-" + line.getTextContent() + "\n");

			NodeList date = element.getElementsByTagName("date");

			line = (Element) date.item(0);

			bw.write("Date  :-" + line.getTextContent() + "\n");

			NodeList msg = element.getElementsByTagName("contents");

			line = (Element) msg.item(0);

			bw.write("contents:- " + line.getTextContent() + "\n\n");
			bw.write("================\n\n");

		}
		bw.close();

	}

}
