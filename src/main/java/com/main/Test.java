package com.main;

import java.util.Calendar;

import java.util.LinkedList;
import java.util.Queue;


public class Test {

	private static final String URL = "http://mail-archives.apache.org/mod_mbox/maven-users/";

	public static void main(String[] args) {
		int year = 2014;
		Calendar now = Calendar.getInstance();

		int month = (now.get(Calendar.MONTH) + 1);
		month = 12;
		Queue<String> queue = new LinkedList<String>();
		String st;
		for (int i = month; i > 0; i--) {
			st = (i < 10 ? "0" + i : i + "");
			queue.add(year + st);
		}

		for (Object object : queue) {
			String element = (String) object;
			System.out.println(element);
			Crawler cr = new Crawler(URL + element);
			Thread th = new Thread(cr);
			th.start();
			// cr.run();
		}

	}

}
