package br.com.cauag.serena.syntax;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.parameters.UnquotedParameter;
import br.com.cauag.serena.syntax.Syntax.SpecialKey;

public class Press extends ExecutableAndParametersReceiver {
	public Press() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		String keyStr = new UnquotedParameter(complement).getValue();
		keyStr = applyParametersAndVariables(keyStr, core);
		SpecialKey key = SpecialKey.valueOf( keyStr );
		core.bot.keyPress( key.getKeyCode() );
		return core.index;
	}
}
