package br.com.cauag.serena.core.syntax.set;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.syntax.FunctionChain;

public class SetDateTime extends FunctionChain {

	public SetDateTime() {
		super();
		addSuccessor("FORMAT", new SetDateTimeFormat());
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
