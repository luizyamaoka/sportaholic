package com.sportaholic.service.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.sportaholic.service.SmtpService;

@Component
public class SmtpServiceImpl implements SmtpService {

	private static final String USERNAME = "sportaholicoficial@gmail.com";
	private static final String PASSWORD = "sP73hj18n#";
	private static final String HOSTNAME = "smtp.gmail.com";
	private static final String PORT = "587";
	
	@Override
	public void sendEmail(String from, String to, String subject, String body) throws Exception {

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", HOSTNAME);
      props.put("mail.smtp.port", PORT);
      
      Session session = Session.getDefaultInstance(props, new Authenticator() {

          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(USERNAME, PASSWORD);
          }

      });
      
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(subject, "UTF-8");
      Multipart mp = new MimeMultipart();
      MimeBodyPart mbp = new MimeBodyPart();
      mbp.setContent(body, "text/html;charset=utf-8");
      mp.addBodyPart(mbp);
      message.setContent(mp);
      message.setSentDate(new java.util.Date());

      Transport.send(message);
	}

}
