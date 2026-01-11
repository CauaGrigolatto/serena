package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Move extends AbstractFunctionExecutor {

	public Move() {
		super();
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
