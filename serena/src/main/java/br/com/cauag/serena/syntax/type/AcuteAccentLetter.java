package br.com.cauag.serena.syntax.type;

import br.com.cauag.serena.core.Core;

public class AcuteAccentLetter implements SpecialChar {
	private final int keyCode;
	
	public AcuteAccentLetter(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public void keyPress(Core core) {
		new AcuteAccent().keyPress(core);
		new AcuteAccent().keyRelease(core);
		core.bot.keyPress(keyCode);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(keyCode);
	}
	
}
