package br.com.cauag.serena.syntax;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.conditions.PreConditions;
import br.com.cauag.serena.core.Core;

public class Paste extends ExecutableAndNotParametersReceiver {
	
	public Paste() {
		super();
		executeIf(PreConditions.NOT_WHEN_DECLARING_BLOCK);
		executeIf(PreConditions.NOT_WHEN_SCHEDULING);
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		core.bot.keyPress(KeyEvent.VK_CONTROL);
		core.bot.keyPress(KeyEvent.VK_V);
		core.bot.delay(100);
		core.bot.keyRelease(KeyEvent.VK_CONTROL);
		core.bot.keyRelease(KeyEvent.VK_V);
		return core.index;
	}
	
}
