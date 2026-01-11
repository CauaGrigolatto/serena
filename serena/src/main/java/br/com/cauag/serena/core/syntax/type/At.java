package br.com.cauag.serena.core.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class At implements SpecialChar {
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_SHIFT);
		core.bot.keyPress(KeyEvent.VK_2);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(KeyEvent.VK_SHIFT);
		core.bot.keyRelease(KeyEvent.VK_2);
	}
}
