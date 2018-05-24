package com.springapirest.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springapirest.util.FileUtil;

public class EmailServiceImpl implements EmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private String to = "vespaluis@gmail.com";
    private String subject = "TOOLVENDOR APP";
    private String messageContent = "Teste de Mensagem";
    private String SMTP_AUTH_PWD;
    
	public EmailServiceImpl() {
		super();
		SMTP_AUTH_PWD  = FileUtil.readProperty("keys.properties", "SENDGRID_PASSWORD");
	}
	
	@Override
    public void sendMailSSL(String toEmail, String emailSubject, String emailBody, String content) {
        this.to = toEmail;
        this.subject = emailSubject;
        this.messageContent = emailBody;
        
        Properties props = new Properties();
        props.put("mail.smtp.host", SERVIDOR_SMTP);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT_SSL);
        props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", SMTP_PORT_SSL);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
                        }
                });

        try {

                final Message message = new MimeMessage(session);
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                try {
                    message.setFrom(new InternetAddress(EMAIL_FROM, SUBJECT_FROM_PERSONAL));
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("Error al agregar InternetAddress: " + e.getMessage(), e);
                }
                message.setSubject(subject);
                if (content.equalsIgnoreCase("text")) message.setText(messageContent);
                else if (content.equalsIgnoreCase("text/html")) message.setContent(messageContent, "text/html");
                message.setSentDate(new Date());
                Transport.send(message);

                System.out.println("SendMailSSL is done.");

        } catch (MessagingException ex) {
                LOGGER.error("Error al enviar mensagem: " + ex.getMessage(), ex);
        }
    }

	@Override
	public void sendMailTSL(String toEmail, String emailSubject, String emailBody, String content, String filename) {
	    this.to = toEmail;
        this.subject = emailSubject;
        this.messageContent = emailBody;
        
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SERVIDOR_SMTP);
        props.put("mail.smtp.port", SMTP_PORT);

        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            }

        });

        try {
            
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            try {
                message.setFrom(new InternetAddress(EMAIL_FROM, SUBJECT_FROM_PERSONAL));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Error al agregar InternetAddress: " + e.getMessage(), e);
            }
            message.setSubject(subject);
            switch (content) {
            	case "text":
            		message.setText(messageContent);
            		break;
            	case "text/html":
            		if (filename != null) {
            			// Create a multipar message
            			Multipart multipart = new MimeMultipart();
                        MimeBodyPart htmlPart = new MimeBodyPart();
                        String htmlContent = emailBody;
                        htmlPart.setText(htmlContent);
                        multipart.addBodyPart(htmlPart);
                        MimeBodyPart attachementPart = new MimeBodyPart();
                        attachementPart.attachFile(new File(filename));
                        attachementPart.setFileName("comprobante.pdf");
                        multipart.addBodyPart(attachementPart);
                        
                        message.setContent(multipart);
            		} else {
            			message.setContent(emailBody, "text/html");
            		}
            		break;
            }
            message.setSentDate(new Date());
            Transport.send(message);
            System.out.println("SendMailTSL is done.");
        } catch (final MessagingException ex) {
            LOGGER.error("Error al enviar mensagem: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
