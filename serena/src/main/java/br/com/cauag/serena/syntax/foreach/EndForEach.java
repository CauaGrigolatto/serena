package br.com.cauag.serena.syntax.foreach;

import java.util.Optional;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.syntax.ExecutableAndNotParametersReceiver;

public class EndForEach extends ExecutableAndNotParametersReceiver {
	public EndForEach() {
		super();
	}
	
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int comeBackTo = Optional.ofNullable(
				core.forEachController.endForEach()
		).orElse(core.index);
		
		return comeBackTo;
	}
}
