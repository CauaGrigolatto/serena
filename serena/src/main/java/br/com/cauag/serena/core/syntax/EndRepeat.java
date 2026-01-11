package br.com.cauag.serena.core.syntax;

import java.util.Optional;

import br.com.cauag.serena.core.Core;

public class EndRepeat extends FunctionChain {
	
	public EndRepeat() {
		super();
	}
	
	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
				core.indexController.endRepeat()
		).orElse(core.index);
		
		return comeBackTo;
	}
}
