package com.firstproject.filipe.services;

import org.springframework.mail.SimpleMailMessage;

import com.firstproject.filipe.domain.Demand;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Demand obj);
	
	void sendEmail(SimpleMailMessage msg);

}
