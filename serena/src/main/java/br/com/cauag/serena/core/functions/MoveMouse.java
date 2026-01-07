package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.commands.parameters.UnquotedParameter;
import br.com.cauag.serena.core.Core;

public class MoveMouse implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String value = new UnquotedParameter(complement).getValue();
		String[] coordinates = value.split(",");
		int x = Integer.parseInt(coordinates[0]);
		int y = Integer.parseInt(coordinates[1]);
		core.bot.mouseMove(x, y);
		return core.index;
	}
	
}
