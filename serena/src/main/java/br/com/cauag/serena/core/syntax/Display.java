
package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Display extends ParameterReceiver {
	public Display() {
		super();
	}
	
	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String message = applyParametersAndVariables(QuotedParameter.valueOf(complement), core);
		System.out.println(message);
		return core.index;
	}
}
