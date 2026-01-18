package br.com.cauag.serena.syntax;

import br.com.cauag.serena.core.Core;

public class EndSchedule extends Executable {
	public EndSchedule() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.scheduleController.isExecuting()) {
			core.scheduleController.shutdown();
			return Core.EXIT_CODE;
		}
		
		core.scheduleController.endSchedule();
		return core.index;
	}
}
