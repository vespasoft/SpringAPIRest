package com.springapirest.service;

public interface EmailService {
	
	public static final String TAG = "SendMail";
    public static final String SERVIDOR_SMTP = "smtp.sendgrid.net"; //or simply "localhost"
    public static final String SMTP_PORT = "587"; //25 or 587"
    public static final String SMTP_PORT_SSL = "993"; //25 or 587"
    public static final String SMTP_AUTH_USER = "app82798421@heroku.com";
    public static final String SMTP_AUTH_PWD  = "ykrecxfx3345";
    public static final String EMAIL_FROM = "service@express.com";
    public static final String SUBJECT_FROM_PERSONAL = "FASHIONEXPRESS APP";

	public void sendMailSSL(String toEmail, String emailSubject, String emailBody, String content);
	
	public void sendMailTSL(String toEmail, String emailSubject, String emailBody, String content, String filename);
	
}
