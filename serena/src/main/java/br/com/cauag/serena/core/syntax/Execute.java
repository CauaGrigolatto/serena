package br.com.cauag.serena.core.syntax;

import java.io.IOException;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.core.Core;

public class Execute extends ParameterReceiver {
	@SuppressWarnings({"deprecation"})
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		try {
			String program = QuotedParameter.valueOf(complement);
			program = applyParametersAndVariables(program, core);
			Runtime.getRuntime().exec(program);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return core.index;
	}

	@Override
	protected boolean canExecute() {
		return true;
	}
}
