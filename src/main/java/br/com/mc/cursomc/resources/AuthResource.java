package br.com.mc.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mc.cursomc.security.JWTUtils;
import br.com.mc.cursomc.security.UserSS;
import br.com.mc.cursomc.services.AuthService;
import br.com.mc.cursomc.services.UserService;

@RestController
@RequestMapping(value = "/auth")
@Validated
public class AuthResource {

	@Autowired
	private JWTUtils ju;
	
	@Autowired
	private AuthService authserv;

	@PostMapping(value = "/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		System.out.println("AuthResource: refreshToken: " + user);
		String token = ju.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/password_reset")
	public ResponseEntity<Void> passwordReset(@RequestParam(name = "email") @NotEmpty @Email String email) {
		System.out.println("AuthResource: passwordReset: " + email);
		authserv.sendNewPassword(email);
		return ResponseEntity.noContent().build();
	}

}
