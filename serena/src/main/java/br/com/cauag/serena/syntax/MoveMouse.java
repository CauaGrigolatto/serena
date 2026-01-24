package br.com.cauag.serena.syntax;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.UnquotedParameter;

public class MoveMouse extends ExecutableAndParametersReceiver {	
	public MoveMouse() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String value = new UnquotedParameter(complement).getValue();
		value = applyParametersAndVariables(value, core);
		String[] coordinates = value.split(",");
		int x = Integer.parseInt(coordinates[0].trim());
		int y = Integer.parseInt(coordinates[1].trim());
		core.bot.mouseMove(x, y);
		return core.index;
	}
}
