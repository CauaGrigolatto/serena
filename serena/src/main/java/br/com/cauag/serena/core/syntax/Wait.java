
package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Wait extends AbstractFunctionExecutor {

	public Wait() {
		super();
		addSuccessor("MILLIS", new WaitMillis());
		addSuccessor("SECONDS", new WaitSeconds());
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
