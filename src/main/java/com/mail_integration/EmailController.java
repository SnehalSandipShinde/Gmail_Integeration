package com.mail_integration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody EmailDetails emailDetails) {
		String status = emailService.sendSimpleMail(emailDetails);

		return status;
	}

	@PostMapping("/sendMailWithAttachment")
	public String sendMailWithAttachment(@ModelAttribute EmailDetails details) throws IOException {
//	        System.err.println(details.getAttachment().getSize());
		String status = emailService.sendMailWithAttachment(details);
		return status;
//	        return "agsdasdg";
	}
}
