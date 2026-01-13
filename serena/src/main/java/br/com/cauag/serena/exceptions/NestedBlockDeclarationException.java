package br.com.cauag.serena.exceptions;

public class NestedBlockDeclarationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NestedBlockDeclarationException(String blockName) {
		super(blockName + " is not declared.");
	}
}
