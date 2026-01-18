package br.com.cauag.serena.syntax.set;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.syntax.NotExecutable;

public class Set extends NotExecutable {
	
	public Set() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("DATE", new SetDate());
	}
}
