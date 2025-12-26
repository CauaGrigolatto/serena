package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Map;

public class RightClick implements CommandExecutor {

	private int rightKey;
	
	@Override
	public void prepare(String arg) {
		this.rightKey = InputEvent.BUTTON3_DOWN_MASK;
	}

	@Override
	public void execute(Robot bot) {
		bot.mousePress(rightKey);
		bot.mouseRelease(rightKey);
	}
	
	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}
}
