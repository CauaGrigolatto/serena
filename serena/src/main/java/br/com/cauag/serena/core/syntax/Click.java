package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Click extends FunctionChain {

	public Click() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("LEFT", new LeftClick());
		addSuccessor("RIGHT", new RightClick());
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
