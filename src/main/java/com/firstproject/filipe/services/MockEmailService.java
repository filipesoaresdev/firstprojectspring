package com.firstproject.filipe.services;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import ch.qos.logback.classic.Logger;

/*
 * Created for test only
 */

public class MockEmailService extends AbstractEmailService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Simulanting sending e-mail");
		LOG.info(msg.toString());
		LOG.info("E-mail sended");
	}

}
