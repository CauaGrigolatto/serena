package br.com.cauag.serena.exceptions;

public class NegativeArgumentException extends IndexableException {
	private static final long serialVersionUID = 1L;

	public NegativeArgumentException(int line) {
		super(line, "argument should not be negative.");
	}
}
