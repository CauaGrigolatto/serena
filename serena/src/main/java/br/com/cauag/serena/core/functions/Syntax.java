package br.com.cauag.serena.core.functions;

public enum Syntax {
	REPEAT("REPEAT"), END_REPEAT("END_REPEAT"),
	BLOCK("BLOCK"), END_BLOCK("END_BLOCK"), CALL("CALL"),
	SCHEDULE("SCHEDULE"), END_SCHEDULE("END_SCHEDULE"),
	INCLUDE("INCLUDE"),
	SET("SET");
	
	private String syntax;
	
	private Syntax(String syntax) {
		this.syntax = syntax;
	}
	
	public boolean sameAs(String s) {
		return syntax.equals(s);
	}
}
