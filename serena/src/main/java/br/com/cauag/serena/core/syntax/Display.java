
package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Display extends ParameterReceiver {
	public Display() {
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
		String message = applyParametersAndVariables(QuotedParameter.valueOf(complement), core);
		System.out.println(message);
		return core.index;
	}
}
