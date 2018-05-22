package com.springapirest.thread;

import com.springapirest.model.User;
import com.springapirest.service.SendEmailService;

public class ThreadSendValidationCodeEmail extends Thread {
	
	private User user;
	private String verificationCode;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        
    }

    public ThreadSendValidationCodeEmail(User user, String verificationCode) {
        this.user = user;
        this.verificationCode = verificationCode;
    }
    
    @Override
    public void run() {
        
    	SendEmailService emailserv = new SendEmailService();
        
        if ( user!=null ) {
        	emailserv.sendValidationCodeEmail(user, verificationCode);
        }
        
    }
}
