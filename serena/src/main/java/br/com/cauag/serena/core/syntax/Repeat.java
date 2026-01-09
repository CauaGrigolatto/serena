package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.core.Core;

public class Repeat extends ReservedWord {
	
	public Repeat() {
		super();
	}
	
	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (! core.indexController.isDeclaringBlock() && ! core.scheduleController.isScheduling()) {
			core.indexController.addRepeat(complement, core.index);
		}
		
		return core.index;
	}
}
