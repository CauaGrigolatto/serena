package br.com.cauag.serena.syntax.assign;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.QuotedParameter;
import br.com.cauag.serena.syntax.ExecutableAndParametersReceiver;

public class Assign extends ExecutableAndParametersReceiver {
	public Assign() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
		addSuccessor("LIST", new AssignList());
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
