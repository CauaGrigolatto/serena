package br.com.cauag.serena.core.commands;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class LeftClick implements CommandExecutor {

	private int leftKey;
	
	@Override
	public void prepare(String arg) {
		this.leftKey = InputEvent.BUTTON1_DOWN_MASK;
	}

	@Override
	public void execute(Robot bot) {
		bot.mousePress(leftKey);
		bot.mouseRelease(leftKey);
	}
	
}
