package br.com.cauag.serena.syntax;

import java.awt.event.InputEvent;

import br.com.cauag.serena.core.Core;

public class RightClick extends FunctionChain {
	
	public RightClick() {
		super();
	}

	@Override
	protected boolean canExecute() {
		return true;
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int rightKey = InputEvent.BUTTON3_DOWN_MASK;
		core.bot.mousePress(rightKey);
		core.bot.mouseRelease(rightKey);
		return core.index;
	}
}
