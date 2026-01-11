package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class End extends FunctionChain {
	public End() {
		super();
		addSuccessor("BLOCK", new EndBlock());
		addSuccessor("REPEAT", new EndRepeat());
		addSuccessor("SCHEDULE", new EndSchedule());
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
