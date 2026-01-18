package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;

public class Click extends NotExecutable {
	public Click() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("LEFT", new LeftClick());
		addSuccessor("RIGHT", new RightClick());
	}
}
