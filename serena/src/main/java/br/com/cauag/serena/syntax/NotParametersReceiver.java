package br.com.cauag.serena.syntax;

import br.com.cauag.serena.core.Core;

public abstract class NotParametersReceiver extends FunctionChain {
	@Override
	protected String applyParametersAndVariables(String token, Core core) {
		return token;
	}	
}
