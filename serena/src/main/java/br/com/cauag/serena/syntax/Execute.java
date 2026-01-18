package br.com.cauag.serena.syntax;

import java.io.IOException;

import br.com.cauag.serena.commands.parameters.QuotedParameter;
import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Execute extends ParameterReceiver {
	
	public Execute() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}
	
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
