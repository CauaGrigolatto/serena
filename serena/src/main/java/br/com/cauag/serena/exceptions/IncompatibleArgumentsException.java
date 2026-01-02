package br.com.cauag.serena.exceptions;

public class IncompatibleArgumentsException extends IndexableException {
	
	private static final long serialVersionUID = 1L;

	public IncompatibleArgumentsException(String blockName, int acceptedArgs, int passedArgs, int line) {
		super(line, blockName + " should receive " + acceptedArgs + (acceptedArgs > 1 ? " args" : " arg") + ", but " + passedArgs + (passedArgs > 1 ? " were" : " was") + " passed.");
	}
}
