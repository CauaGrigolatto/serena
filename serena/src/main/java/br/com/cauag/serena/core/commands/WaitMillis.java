package br.com.cauag.serena.core.commands;

import java.awt.Robot;

public class WaitMillis implements CommandExecutor {

	private int millis;
	
	@Override
	public void prepare(String arg) {
		this.millis = Integer.parseInt(arg);
	}

	@Override
	public void execute(Robot bot) {
		bot.delay(millis);
	}

}
