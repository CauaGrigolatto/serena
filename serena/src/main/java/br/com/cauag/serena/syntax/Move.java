package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Move extends FunctionChain {

	public Move() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("MOUSE", new MoveMouse());
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
