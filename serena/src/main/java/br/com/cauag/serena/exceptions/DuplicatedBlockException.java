package br.com.cauag.serena.exceptions;

public class DuplicatedBlockException extends IndexableException {
	
	private static final long serialVersionUID = 1L;

	public DuplicatedBlockException(String blockName, int line) {
		super(line, "the block " + blockName + " is already declared.");
	}
}
