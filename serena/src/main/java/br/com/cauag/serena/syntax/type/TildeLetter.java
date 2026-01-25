package br.com.cauag.serena.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class TildeLetter implements SpecialChar {
	private final int keyCode;
	
	public TildeLetter(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_DEAD_TILDE);
		core.bot.keyRelease(KeyEvent.VK_DEAD_ACUTE);
		core.bot.keyPress(keyCode);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(keyCode);
	}
}
