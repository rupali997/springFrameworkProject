package com.emailapi.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {

		boolean flag = false;
		// Constant
		String from = "rupaliborkar1997@gmail.com";
		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES : " + properties);

		// Setting important information to properties

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step 1: get Session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("rupaliborkar1997@gmail.com", "Rupali9145@#");
			}

		});

		session.setDebug(true);

		// step 2: compose message

		MimeMessage m = new MimeMessage(session);

		try {
			// set from
			m.setFrom(from);

			// set to
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// set subject
			m.setSubject(subject);

			// set message
			m.setText(message);

			// setp 3: send the message using transport class
			Transport.send(m);
			System.out.println("Send Successfully!!");

			flag = true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}
}
