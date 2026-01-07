
package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Display implements FunctionExecutor {	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String message = QuotedParameter.valueOf(complement);
		System.out.println(message);
		return core.index;
	}
}
