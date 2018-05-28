package com.springapirest.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.springapirest.util.FileUtil;

public class FileUtilTest {

	@Test
	public void getProperty() {
		String sendGridPassword = FileUtil.getProperty("application.properties", "spring.sendgrid.password");
		if (sendGridPassword.isEmpty() || sendGridPassword==null)
			fail("The method getProperty is failed.");
		else
			System.out.println("getProperty: " + sendGridPassword);
	}

}
