package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Click extends ReservedWord {

	public Click() {
		super();
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
