package br.com.cauag.serena.core.syntax;

import java.awt.event.InputEvent;

import br.com.cauag.serena.core.Core;

public class RightClick extends ReservedWord {
	
	public RightClick() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		int rightKey = InputEvent.BUTTON3_DOWN_MASK;
		core.bot.mousePress(rightKey);
		core.bot.mouseRelease(rightKey);
		return core.index;
	}
}
