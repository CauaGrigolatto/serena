package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.UnquotedParameter;
import br.com.cauag.serena.core.Core;

public class MoveMouse extends ParameterReceiver {
	
	public MoveMouse() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String value = new UnquotedParameter(complement).getValue();
		value = applyParametersAndVariables(value, core);
		String[] coordinates = value.split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		core.bot.mouseMove(x, y);
		return core.index;
	}
	
}
