package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Paste implements CommandExecutor {
		
	@Override
	public void prepare(String arg) {}

	@Override
	public void execute(Robot bot) {
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_V);
		bot.delay(100);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.keyRelease(KeyEvent.VK_V);
	}
}
