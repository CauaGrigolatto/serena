package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Set extends ReservedWord {
	
	public Set() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String[] splittedArg = complement.split(" ", 2);
		
		String config = splittedArg[0];
		
		if (config == null) {
			throw new IllegalArgumentException();
		}
		
		String value = new QuotedParameter(splittedArg[1]).getValue();
		
		core.configController.setConfig(config, value);
		
		return core.index;
	}
}
