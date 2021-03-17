package br.com.mc.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService implements EmailService {
	private static final Logger log = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	private MailSender ms;
	
	@Override
	public void sendMail(SimpleMailMessage msg) {
		log.info("Enviando email");
		ms.send(msg);
		log.info("Email enviado");
	}

}
