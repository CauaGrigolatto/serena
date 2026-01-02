package br.com.cauag.serena.exceptions;

public class InvalidSerenaFile extends IndexableException {

	private static final long serialVersionUID = 1L;
	
	public InvalidSerenaFile(int line) {
		super(line, "the specified path is not a Serena file.");
	}
	
	public InvalidSerenaFile() {
		super("The specified path is not a Serena file.");
	}
}
