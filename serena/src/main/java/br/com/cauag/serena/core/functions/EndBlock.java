package br.com.cauag.serena.core.functions;

import java.util.Optional;

import br.com.cauag.serena.core.Core;

public class EndBlock implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement) {
		int comeBackTo = Optional.ofNullable(
			Core.indexController.endBlock()
		).orElse(Core.index);
		
		return comeBackTo;
	}
}
