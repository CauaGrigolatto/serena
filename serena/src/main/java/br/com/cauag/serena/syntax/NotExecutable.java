package br.com.cauag.serena.syntax;

import br.com.cauag.serena.core.Core;

public abstract class NotExecutable extends NotParametersReceiver {

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		return -2;
	}

	@Override
	protected boolean canExecute() {
		return false;
	}
	
}
