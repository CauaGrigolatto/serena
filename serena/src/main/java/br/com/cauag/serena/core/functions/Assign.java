package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Assign implements FunctionExecutor {

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String[] splittedArgs = complement.split(" ", 2);
		
		String varName = splittedArgs[0].trim();
		String varValue = QuotedParameter.valueOf(splittedArgs[1]).trim();
		
		core.variablesController.setVariable(varName, varValue);
		
		return core.index;
	}
	
}
