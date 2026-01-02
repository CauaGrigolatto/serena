package br.com.cauag.serena.core.functions;

import java.util.Optional;

import br.com.cauag.serena.core.Core;

public class EndRepeat implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
				core.indexController.endRepeat()
		).orElse(core.index);
		
		return comeBackTo;
	}
}
