package com.springapirest.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	private FileUtil() {
	}
	
	public static boolean setProperty(String file, String property, String value) {
		

		try {
			Properties prop = new Properties();
			OutputStream output = new FileOutputStream(file);

			prop.setProperty(property, value);

			prop.store(output, null);
			
			return true;

		} catch (IOException io) {
			LOGGER.error(io.getMessage());
			return false;
		}
	}
	
	public static String getProperty(String file, String property) {

		try {
			Properties prop = new Properties();
			InputStream input = null;
			
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			input = classloader.getResourceAsStream(file);
			
			prop.load(input);
			
			return prop.getProperty(property);

		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
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
			LOGGER.error(e.getMessage());
		}
			
		return result.toString();

	  }
	
}
