package br.com.cauag.serena.syntax;

import java.util.Optional;

import br.com.cauag.serena.core.Core;

public class EndBlock extends ExecutableAndNotParametersReceiver {
	public EndBlock() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
			core.indexController.endBlock()
		).orElse(core.index);
		return comeBackTo;
	}
}
