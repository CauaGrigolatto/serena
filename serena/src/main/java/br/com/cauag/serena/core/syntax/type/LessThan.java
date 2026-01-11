package br.com.cauag.serena.core.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class LessThan implements SpecialChar {
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_SHIFT);
		core.bot.keyPress(KeyEvent.VK_COMMA);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(KeyEvent.VK_SHIFT);
		core.bot.keyRelease(KeyEvent.VK_COMMA);
	}
}
