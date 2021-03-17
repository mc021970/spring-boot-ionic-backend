package br.com.mc.cursomc.services;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

import br.com.mc.cursomc.domain.Pedido;
import br.com.mc.cursomc.utils.ConfigUtils;

public interface EmailService {

	default void sendOrderConfirmationMessage(Pedido pedido) {
		SimpleMailMessage sm = prepareEMailFromPedido(pedido);
		sendMail(sm);
	}
	
	default SimpleMailMessage prepareEMailFromPedido(Pedido pedido) {
		String sender = ConfigUtils.getProperty("email.default.sender");
		System.out.println("Sender: " + sender);
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Recebemos seu pedido: " + pedido.getId());
		sm.setSentDate(new Date());
		sm.setText(pedido.toString());
		return sm;
	}

	void sendMail(SimpleMailMessage msg);
	
}
