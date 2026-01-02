package br.com.cauag.serena.exceptions;

public class IndexableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IndexableException(String message) {
		super(message);
	}
	
	public IndexableException(int line, String message) {
		super("At line " + line + ": " + message);
	}
}
