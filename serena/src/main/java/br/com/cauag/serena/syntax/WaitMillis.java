package br.com.cauag.serena.syntax;

import java.time.Duration;

import br.com.cauag.serena.commands.parameters.PositiveParameter;
import br.com.cauag.serena.core.Core;

public class WaitMillis extends ExecutableAndParametersReceiver {
	public WaitMillis() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String valueStr = applyParametersAndVariables(complement, core);
		int value = new PositiveParameter(Integer.parseInt(valueStr)).getValue();
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
