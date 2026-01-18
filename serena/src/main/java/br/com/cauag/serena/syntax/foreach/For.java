package br.com.cauag.serena.syntax.foreach;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.syntax.NotExecutable;

public class For extends NotExecutable {
	public For() {
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("EACH", new ForEach());
	}
}
