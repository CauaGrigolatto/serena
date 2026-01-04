package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Set implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
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
