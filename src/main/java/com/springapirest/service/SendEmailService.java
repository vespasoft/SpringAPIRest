package com.springapirest.service;

import com.springapirest.model.User;
import com.springapirest.util.FileUtil;

public class SendEmailService {
	
	private EmailServiceImpl emailService = new EmailServiceImpl();
	
    public void sendWelcomeEmail(User user) {
        String toEmail = user.getUsername();
        String emailSubject = "Bienvenido a A&F Beauty Studio";
        String emailBody = FileUtil.getFile("templates/email/welcome.html");
        
        emailBody = emailBody.replace("${username}", user.getUsername());
        emailBody = emailBody.replace("${password}", user.getPassword());
    
    	emailService.sendMail(toEmail, emailSubject, emailBody, "text/html", null);
    }

    public void sendValidationCodeEmail(User user, String verificationCode) {
        String toEmail = user.getUsername();
        String emailSubject = "Validar correo eléctronico";
        String emailBody = FileUtil.getFile("templates/email/validation_code.html");
        
        emailBody = emailBody.replace("${verificationCode}", verificationCode);
        
        emailService.sendMail(toEmail, emailSubject, emailBody, "text/html", null);
    }

}
