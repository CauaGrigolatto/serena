package br.com.cauag.serena.syntax;

import java.util.Optional;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class EndRepeat extends Executable {	
	public EndRepeat() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
				core.indexController.endRepeat()
		).orElse(core.index);
		
		return comeBackTo;
	}
}
