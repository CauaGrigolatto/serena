package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class Repeat implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement) {
		if (! Core.indexController.isDeclaringBlock()) {
			Core.indexController.addRepeat(complement, Core.index);
		}
		
		return Core.index;
	}
}
