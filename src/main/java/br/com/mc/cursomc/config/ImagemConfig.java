package br.com.mc.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mc.cursomc.services.DBImagemService;
import br.com.mc.cursomc.services.ImagemService;

@Configuration
public class ImagemConfig {
	
	@Value("${imagemservice.impl:}")
	private String imgservimpl;

	@Bean
	public ImagemService imagemService() {
		if ("s3".equals(imgservimpl)) {
			
		}
		return new DBImagemService();
	}
}
