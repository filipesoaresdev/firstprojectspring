package com.firstproject.filipe.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.firstproject.filipe.domain.Demand;

public interface EmailService {

	void sendOrderConfirmationEmail(Demand obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Demand obj);

	void sendHtmlEmail(MimeMessage msg);

}
