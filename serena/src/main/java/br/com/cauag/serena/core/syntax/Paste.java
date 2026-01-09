package br.com.cauag.serena.core.syntax;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class Paste extends ReservedWord {
	
	public Paste() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		core.bot.keyPress(KeyEvent.VK_CONTROL);
		core.bot.keyPress(KeyEvent.VK_V);
		core.bot.delay(100);
		core.bot.keyRelease(KeyEvent.VK_CONTROL);
		core.bot.keyRelease(KeyEvent.VK_V);
		return core.index;
	}
	
}
