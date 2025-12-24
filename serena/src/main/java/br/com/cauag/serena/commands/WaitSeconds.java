package br.com.cauag.serena.commands;

import java.awt.Robot;

public class WaitSeconds implements CommandExecutor {

	private int seconds;
	
	@Override
	public void prepare(String arg) {
		this.seconds = Integer.parseInt(arg);
	}

	@Override
	public void execute(Robot bot) {
		bot.delay(seconds * 1000);
	}

}
