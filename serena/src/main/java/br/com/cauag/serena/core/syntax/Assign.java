package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Assign extends ParameterReceiver {

	public Assign() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}
	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String[] splittedArgs = complement.split(" ", 2);
		
		String varName = splittedArgs[0].trim();
		String varValue = QuotedParameter.valueOf(splittedArgs[1]).trim();
		
		core.variablesController.setVariable(varName, varValue);
		
		return core.index;
	}
	
}
