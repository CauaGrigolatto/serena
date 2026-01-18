package br.com.cauag.serena.syntax.foreach;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.syntax.NotExecutable;

public class EndFor extends NotExecutable {
	public EndFor() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("EACH", new EndForEach());
	}
}
