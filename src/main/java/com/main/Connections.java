package com.main;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

/**
 * This Class is responsible to make application connection restart.
 * 
 * @author Manish Kumar
 * @version 1.0
 * @since 10-April-2015
 */

public class Connections {

	private static final Logger logger = Logger.getLogger(Connections.class);

	public boolean checkConnection(HttpURLConnection conn) {

		try {
			conn.connect();
			while (!(conn.getResponseCode() / 200 == 2)) {

				logger.info("Waiting for the connection..");
				Thread.sleep(5000);
				conn.connect();
			}
		} catch (IOException | InterruptedException conException) {

			// Have to keep log
			logger.info(conException.getMessage());

		}

		return true;

	}
}
