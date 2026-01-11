package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Set extends ParameterReceiver {
	
	public Set() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String[] splittedArg = complement.split(" ", 2);
		
		String config = splittedArg[0];
		
		if (config == null) {
			throw new IllegalArgumentException();
		}
		
		String value = new QuotedParameter(splittedArg[1]).getValue();
		value = applyParametersAndVariables(value, core);
		
		core.configController.setConfig(config, value);
		
		return core.index;
	}
}
