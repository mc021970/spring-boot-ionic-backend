package br.com.mc.cursomc.services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mc.cursomc.dao.ClienteDAO;
import br.com.mc.cursomc.domain.Cliente;
import br.com.mc.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteDAO clidao;
	
	@Autowired
	private BCryptPasswordEncoder bpe;
	
	@Autowired
	private EmailService eserv;
	
	private Random r = new Random();
	
	public void sendNewPassword(String email) {
		Cliente c = clidao.findByEmail(email);
		if (c == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newpass = createNewPassword(10);
		c.setSenha(bpe.encode(newpass));
		clidao.save(c);
		eserv.sendNewPasswordEmail(c, newpass);
	}


    static char[] SYMBOLS = "^$*.[]{}()?-\"!@#%&/\\,><':;|_~`".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^$*.[]{}()?-\"!@#%&/\\,><':;|_~`".toCharArray();
    static Random rand = new SecureRandom();

    public static String createNewPassword(int length) {
        assert length >= 4;
        char[] password = new char[length];

        //get the requirements out of the way
        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[2] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[3] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        //populate rest of the password with random chars
        for (int i = 4; i < length; i++) {
            password[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        //shuffle it up
        for (int i = 0; i < password.length; i++) {
            int randomPosition = rand.nextInt(password.length);
            char temp = password[i];
            password[i] = password[randomPosition];
            password[randomPosition] = temp;
        }

        return new String(password);
    }
}
