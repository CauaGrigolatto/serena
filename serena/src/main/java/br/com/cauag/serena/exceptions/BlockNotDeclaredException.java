package br.com.cauag.serena.exceptions;

public class BlockNotDeclaredException extends IndexableException {
	
	private static final long serialVersionUID = 1L;

	public BlockNotDeclaredException(String blockName, int index) {
		super(index, blockName + " is not declared.");
	}
}
