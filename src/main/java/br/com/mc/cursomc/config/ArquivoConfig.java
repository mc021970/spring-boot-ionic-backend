package br.com.mc.cursomc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mc.cursomc.services.DBArquivoService;
import br.com.mc.cursomc.services.ArquivoService;

@Configuration
public class ArquivoConfig {
	
	@Value("${arquivoservice.impl:}")
	private String arquivoservice;

	@Bean
	public ArquivoService arquivoService() {
		if ("s3".equals(arquivoservice)) {
			
		}
		return new DBArquivoService();
	}
}
