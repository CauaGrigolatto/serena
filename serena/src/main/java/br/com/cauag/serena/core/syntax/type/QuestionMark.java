package br.com.cauag.serena.core.syntax.type;

import java.awt.event.KeyEvent;

import br.com.cauag.serena.core.Core;

public class QuestionMark implements SpecialChar {
	@Override
	public void keyPress(Core core) {
		core.bot.keyPress(KeyEvent.VK_ALT_GRAPH);
		core.bot.keyPress(KeyEvent.VK_W);
	}

	@Override
	public void keyRelease(Core core) {
		core.bot.keyRelease(KeyEvent.VK_ALT_GRAPH);
		core.bot.keyRelease(KeyEvent.VK_W);
	}
}
