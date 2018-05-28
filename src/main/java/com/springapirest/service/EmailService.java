package com.springapirest.service;

public interface EmailService {
	
	public void sendMail(String toEmail, String emailSubject, String emailBody, String content, String filename);
	
}
