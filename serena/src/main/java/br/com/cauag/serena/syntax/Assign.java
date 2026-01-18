package br.com.cauag.serena.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Assign extends ParameterReceiver {

	public Assign() {
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
		String[] splittedArgs = complement.split(" ", 2);
		
		String varName = splittedArgs[0].trim();
		String varValue = QuotedParameter.valueOf(splittedArgs[1]).trim();
		varValue = applyParametersAndVariables(varValue, core);
		
		core.variablesController.setVariable(varName, varValue);
		
		return core.index;
	}
}
