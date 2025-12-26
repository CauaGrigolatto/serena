package br.com.cauag.serena.commands;

import java.awt.Robot;
import java.util.Map;

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

	@Override
	public void applyParameters(Map<String, String> parameters) {
		
	}
	
}
