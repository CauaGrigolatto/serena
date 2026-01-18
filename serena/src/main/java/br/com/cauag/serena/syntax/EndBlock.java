package br.com.cauag.serena.syntax;

import java.util.Optional;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class EndBlock extends Executable {
	public EndBlock() {
		super();
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
			core.indexController.endBlock()
		).orElse(core.index);
		return comeBackTo;
	}
}
