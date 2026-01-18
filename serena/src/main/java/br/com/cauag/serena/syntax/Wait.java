
package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;

public class Wait extends NotExecutable {
	public Wait() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("MILLIS", new WaitMillis());
		addSuccessor("SECONDS", new WaitSeconds());
	}
}
