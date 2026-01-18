package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Repeat extends ExecutableAndParametersReceiver {
	public Repeat() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String times = applyParametersAndVariables(complement, core);
		core.indexController.addRepeat(times, core.index);
		return core.index;
	}
}
