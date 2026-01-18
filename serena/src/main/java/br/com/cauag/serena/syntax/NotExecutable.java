package br.com.cauag.serena.syntax;

import br.com.cauag.serena.core.Core;

public abstract class NotExecutable extends FunctionChain {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		return Core.EXIT_CODE;
	}

	@Override
	protected boolean canExecute() {
		return false;
	}
}
