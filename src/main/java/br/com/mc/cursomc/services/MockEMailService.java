package br.com.mc.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEMailService implements EmailService {
	private static final Logger log = LoggerFactory.getLogger(MockEMailService.class);

	@Override
	public void sendMail(SimpleMailMessage msg) {
		log.info("Enviando email");
		log.info(msg.toString());
		log.info("Email enviado");
	}
}
