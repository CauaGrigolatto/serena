package br.com.cauag.serena.exceptions;

public class IndexableNestedBlockDeclarationException extends IndexableException {
	
	private static final long serialVersionUID = 1L;

	public IndexableNestedBlockDeclarationException(int index, String blockName) {
		super(index, blockName + " is not declared.");
	}
}
