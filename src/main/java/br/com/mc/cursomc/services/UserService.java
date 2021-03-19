package br.com.mc.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.mc.cursomc.security.UserSS;

public class UserService {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			System.out.println("authenticated: " + e.getMessage());
			return null;
		}
	}
}
