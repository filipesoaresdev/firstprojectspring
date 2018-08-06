package com.firstproject.filipe.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.firstproject.filipe.domain.Demand;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Demand obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromDemand(obj);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromDemand(Demand obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
}
