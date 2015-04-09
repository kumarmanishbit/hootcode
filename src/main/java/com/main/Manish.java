package com.main;

import java.net.URL;


public class Manish {

	public static void main(String[] args) throws Exception {

	String urlName = "http://mail-archives.apache.org/mod_mbox/maven-users/";

	URL url=new URL(urlName);
	System.out.println(url.getRef());
	}
}
