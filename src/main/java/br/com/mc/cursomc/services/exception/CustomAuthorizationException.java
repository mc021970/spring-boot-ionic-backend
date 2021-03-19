package br.com.mc.cursomc.services.exception;

public class CustomAuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomAuthorizationException() {
		super();
	}

	public CustomAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomAuthorizationException(String message) {
		super(message);
	}

	public CustomAuthorizationException(Throwable cause) {
		super(cause);
	}

}
