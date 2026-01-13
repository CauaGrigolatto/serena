package br.com.cauag.serena.exceptions;

public class NegativeArgumentException extends Exception {
	private static final long serialVersionUID = 1L;

	public NegativeArgumentException() {
		super("argument should not be negative.");
	}
}
