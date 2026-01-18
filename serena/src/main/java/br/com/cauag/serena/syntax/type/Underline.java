package br.com.cauag.serena.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class Underline implements SpecialChar {
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_SHIFT);
		core.bot.keyPress(KeyEvent.VK_MINUS);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(KeyEvent.VK_SHIFT);
		core.bot.keyRelease(KeyEvent.VK_MINUS);
	}
}
