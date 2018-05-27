package com.springapirest.service;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Session;
import java.io.File;
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
	
	public static final String TAG = "SendMail";
    public static final String SERVIDOR_SMTP = "smtp.sendgrid.net"; //or simply "localhost"
    public static final String SMTP_PORT = "587"; //25 or 587"
    public static final String SMTP_PORT_SSL = "993"; //25 or 587"
    public static final String SMTP_AUTH_USER = "app82798421@heroku.com";
    public static final String EMAIL_FROM = "service@express.com";
    public static final String SUBJECT_FROM_PERSONAL = "FASHIONEXPRESS APP";

    
	public EmailServiceImpl() {
		super();
	}
	
	@Override
	public void sendMail(String toEmail, String emailSubject, String emailBody, String content, String filename) {
		String to = toEmail;
	    String subject = emailSubject;
	    String messageContent = emailBody;
	    String authenticationPassword = FileUtil.readProperty("keys.properties", "SENDGRID_PASSWORD");
	    
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SERVIDOR_SMTP);
        props.put("mail.smtp.port", SMTP_PORT);

        final Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, authenticationPassword);
            }

        });

        try {
            
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.setFrom(new InternetAddress(EMAIL_FROM, SUBJECT_FROM_PERSONAL));
            
            message.setSubject(subject);
            if (content.equalsIgnoreCase("text/html")) {
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
            } else {
            	message.setText(messageContent);
            }
            message.setSentDate(new Date());
            Transport.send(message);
            LOGGER.debug("SendMailTSL is done.");
        } catch (final MessagingException ex) {
            LOGGER.error("Error al enviar mensagem: " + ex.getMessage(), ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

}
