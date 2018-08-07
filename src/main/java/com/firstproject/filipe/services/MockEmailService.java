package com.firstproject.filipe.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

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
	
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		LOG.info("Simulanting sending e-mail");
		LOG.info(msg.toString());
		LOG.info("E-mail sended");
	}

}
