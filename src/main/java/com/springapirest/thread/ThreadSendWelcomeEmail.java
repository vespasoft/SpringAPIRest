package com.springapirest.thread;

import com.springapirest.model.User;
import com.springapirest.service.SendEmailService;

public class ThreadSendWelcomeEmail extends Thread {
	
	private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        
    }

    public ThreadSendWelcomeEmail(User user) {
        this.user = user;
    }
    
    @Override
    public void run() {
        
    	SendEmailService emailserv = new SendEmailService();
        
        if ( user!=null ) {
        	emailserv.sendWelcomeEmail(user);
        }
        
    }
}
