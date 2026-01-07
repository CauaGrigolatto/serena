package br.com.cauag.serena.core.functions;

import java.time.Duration;

import br.com.cauag.serena.commands.parameters.PositiveParameter;
import br.com.cauag.serena.core.Core;

public class WaitSeconds implements FunctionExecutor {
	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		int value = new PositiveParameter(Integer.parseInt(complement)).getValue();
		try {			
			Thread.sleep(
				Duration.ofSeconds(
					value
				)
			);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		return core.index;
	}
	
}
