package br.com.cauag.serena.core.syntax;

import br.com.cauag.serena.commands.parameters.UnquotedParameter;
import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.functions.Syntax.SpecialKey;

public class Press extends ParameterReceiver {
	
	public Press() {
		super();
	}
	
	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		String keyStr = new UnquotedParameter(complement).getValue();
		SpecialKey key = SpecialKey.valueOf( keyStr );
		core.bot.keyPress( key.getKeyCode() );
		return core.index;
	}
}
