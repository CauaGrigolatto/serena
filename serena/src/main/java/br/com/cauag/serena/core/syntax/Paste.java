package br.com.cauag.serena.core.syntax;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;
import br.com.cauag.serena.core.conditions.PreConditions;

public class Paste extends FunctionChain {
	
	public Paste() {
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
		core.bot.keyPress(KeyEvent.VK_CONTROL);
		core.bot.keyPress(KeyEvent.VK_V);
		core.bot.delay(100);
		core.bot.keyRelease(KeyEvent.VK_CONTROL);
		core.bot.keyRelease(KeyEvent.VK_V);
		return core.index;
	}
	
}
