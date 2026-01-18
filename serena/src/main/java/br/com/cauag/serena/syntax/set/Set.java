package br.com.cauag.serena.syntax.set;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.FunctionChain;

public class Set extends FunctionChain {
	
	public Set() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("DATE", new SetDate());
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		return -2;
	}

	@Override
	protected boolean canExecute() {
		return false;
	}
}
