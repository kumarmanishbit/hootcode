package com.main;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * This Class is responsible to make application connection restart. 
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */


public class Connections {

	public boolean checkConnection(HttpURLConnection conn) {

		try {
			conn.connect();
			while (!(conn.getResponseCode() / 200 == 2)) {

				System.out.println("Waiting for connection...");
				Thread.sleep(5000);
				conn.connect();
			}
		} catch (IOException | InterruptedException conException) {
			
			// Have to keep log

			System.out.println(conException.getMessage());
		}

		return true;

	}
}
