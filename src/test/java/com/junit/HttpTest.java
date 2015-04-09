package com.junit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class HttpTest {

	@Test
	public void testGetContentOk() throws Exception
	{
		URL obj = new URL("http://mail-archives.apache.org/mod_mbox/maven-users/");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		
		assertTrue(responseCode==200);
	}

	
}


