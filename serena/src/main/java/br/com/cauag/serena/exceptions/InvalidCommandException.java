package br.com.cauag.serena.exceptions;

public class InvalidCommandException extends IndexableException {

	private static final long serialVersionUID = 1L;

	public InvalidCommandException(String commandName, int line) {
		super(line, commandName + " is not a valid command.");
	}
}
