package br.com.cauag.serena.core.functions;

import java.awt.event.InputEvent;

import br.com.cauag.serena.core.Core;

public class LeftClick implements FunctionExecutor {
	@Override
	public int executeAndGetIndex(String complement, Core core) throws Exception {
		if (core.indexController.isDeclaringBlock() || core.scheduleController.isScheduling()) return core.index;
		int leftKey = InputEvent.BUTTON1_DOWN_MASK;
		core.bot.mousePress(leftKey);
		core.bot.mouseRelease(leftKey);
		return core.index;
	}
}
