package br.com.cauag.serena.syntax;

import java.awt.event.InputEvent;

import br.com.cauag.serena.core.Core;

public class LeftClick extends Executable {
	
	public LeftClick() {
		super();
	}

	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		int leftKey = InputEvent.BUTTON1_DOWN_MASK;
		core.bot.mousePress(leftKey);
		core.bot.mouseRelease(leftKey);
		return core.index;
	}
}
