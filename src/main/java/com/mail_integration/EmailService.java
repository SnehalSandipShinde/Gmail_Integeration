package com.mail_integration;

public interface EmailService {
	
	String sendSimpleMail(EmailDetails details);
	
	public String sendMailWithAttachment(EmailDetails details);

}
