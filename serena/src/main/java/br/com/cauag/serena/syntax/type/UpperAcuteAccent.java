package br.com.cauag.serena.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class UpperAcuteAccent implements SpecialChar {
	private final int keyCode;
	
	public UpperAcuteAccent(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_DEAD_ACUTE);
		core.bot.keyPress(KeyEvent.VK_SHIFT);
		core.bot.keyPress(keyCode);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(KeyEvent.VK_SHIFT);
		core.bot.keyRelease(keyCode);
	}
}
