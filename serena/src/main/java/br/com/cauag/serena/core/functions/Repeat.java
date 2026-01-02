package br.com.cauag.serena.core.functions;

import br.com.cauag.serena.core.Core;

public class Repeat implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (! core.indexController.isDeclaringBlock()) {
			core.indexController.addRepeat(complement, core.index);
		}
		
		return core.index;
	}
}
