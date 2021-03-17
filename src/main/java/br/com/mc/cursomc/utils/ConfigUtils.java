package br.com.mc.cursomc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtils implements EnvironmentAware {

	@Autowired
	private static Environment stenv;
 
    @Override
    public void setEnvironment(Environment env) {
        stenv = env;
    }

	@Autowired
	private static ConfigUtils cu;
	
	public static String getProperty(String nome) {
		return stenv.getProperty(nome);
	}
	
}
