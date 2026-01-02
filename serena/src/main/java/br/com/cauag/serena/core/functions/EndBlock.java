package br.com.cauag.serena.core.functions;

import java.util.Optional;

import br.com.cauag.serena.core.Core;

public class EndBlock implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
			core.indexController.endBlock()
		).orElse(core.index);
		
		return comeBackTo;
	}
}
