package com.mail_integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;


	@Override
	public String sendSimpleMail(EmailDetails details) {
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			// sending the mail
			javaMailSender.send(mailMessage);
			return "Mail message sent successfully";

		} catch (Exception e) {
			e.printStackTrace();
			return "Error while Sending Mail";
		}
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper;

		try {

			// Setting multipart as true for attachments to
			// be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom("Snehal Shinde<"+sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setSubject(details.getSubject());
			mimeMessageHelper.setText(details.getMsgBody());

			// Adding the attachment
			mimeMessageHelper.addAttachment(details.getAttachment().getOriginalFilename(), details.getAttachment());

			// Sending the mail
			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		}

		// Catch block to handle MessagingException
		catch (MessagingException e) {
			// Display message when exception occurred
			return "Error while sending mail!!!";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
