package edu.chalkboarduni.uniregistrationsystem.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MyEmailService  {
	
	@Autowired
	private JavaMailSender javaMailSender;
	 
	public void sendOtpMessage(String to, String subject, String message) throws Exception {
		 
		MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(to);
        helper.setText(message, true);
        helper.setSubject(subject);
		
		//Uncomment to send mail
		javaMailSender.send(msg);
	}
}
