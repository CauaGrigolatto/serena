package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class EndSchedule implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.scheduleController.isExecuting()) {
			core.scheduleController.shutdown();
			return -2;
		}
		
		core.scheduleController.endSchedule(core.index);
		return core.index;
	}
}
