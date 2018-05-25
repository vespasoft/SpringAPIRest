package com.springapirest.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
	
	private static final Logger LOGGER = Logger.getLogger("com.springapirest.Control");
	
	private FileUtil() {
	}
	
	public static boolean writeProperty(String file, String property, String value) {
		

		try {
			Properties prop = new Properties();
			OutputStream output = new FileOutputStream(file);

			prop.setProperty(property, value);

			prop.store(output, null);
			
			return true;

		} catch (IOException io) {
			LOGGER.log(Level.WARNING, io.getMessage());
			return false;
		}
	}
	
	public static String readProperty(String file, String property) {

		try {
			Properties prop = new Properties();
			InputStream input = null;
			
			input = new FileInputStream(file);

			prop.load(input);

			return prop.getProperty(property);

		} catch (IOException ex) {
			LOGGER.log(Level.WARNING, ex.getMessage());
			return null;
		}
	}
	
}
