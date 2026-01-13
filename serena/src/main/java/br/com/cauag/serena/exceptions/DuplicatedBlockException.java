package br.com.cauag.serena.exceptions;

public class DuplicatedBlockException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DuplicatedBlockException(String blockName) {
		super("the block " + blockName + " is already declared.");
	}
}
