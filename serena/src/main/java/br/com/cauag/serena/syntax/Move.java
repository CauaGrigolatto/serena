package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;

public class Move extends NotExecutable {
	public Move() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("MOUSE", new MoveMouse());
	}
}
