package com.springapirest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;
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
	
	public static String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		//Get file from resources folder
		ClassLoader classLoader = FileUtil.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
			
		return result.toString();

	  }
	
}
