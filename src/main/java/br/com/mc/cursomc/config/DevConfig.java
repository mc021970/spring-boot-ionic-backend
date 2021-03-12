package br.com.mc.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.mc.cursomc.services.DBService;
import br.com.mc.cursomc.utils.TestUtils;

@Configuration
@Profile("dev")
public class DevConfig {
	
	public DevConfig() {
		TestUtils.logDestacado("DevConfig()");
	}
	
	@Autowired
	private DBService dbserv;

	@Bean
	public boolean initDatabase() throws Exception {

		TestUtils.logDestacado("DevConfig.initDatabase()");
		dbserv.initTestDB();
		
		return true;
	}
}
