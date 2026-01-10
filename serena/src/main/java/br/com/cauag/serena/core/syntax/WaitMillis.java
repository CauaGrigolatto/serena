package br.com.cauag.serena.core.syntax;

import java.time.Duration;

import br.com.cauag.serena.commands.parameters.PositiveParameter;
import br.com.cauag.serena.core.Core;

public class WaitMillis extends ParameterReceiver {
	public WaitMillis() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		int value = new PositiveParameter(Integer.parseInt(complement)).getValue();
		try {			
			Thread.sleep(
				Duration.ofMillis(
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
