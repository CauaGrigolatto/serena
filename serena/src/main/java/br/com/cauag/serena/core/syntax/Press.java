package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.UnquotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;
import br.com.cauag.serena.core.syntax.Syntax.SpecialKey;

public class Press extends ParameterReceiver {
	
	public Press() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}
	
	@Override
	protected boolean canExecute() {
		return true;
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
